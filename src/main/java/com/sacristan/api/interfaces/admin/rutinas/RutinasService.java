package com.sacristan.api.interfaces.admin.rutinas;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.content.category.Category;
import com.sacristan.api.global.entities.content.category.CategoryModelService;
import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.global.entities.content.rotuine.RoutineModelService;
import com.sacristan.api.global.entities.content.rotuine.RoutineSpecification;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.content.sequence.SequenceModelService;
import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegment;
import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegmentModelService;
import com.sacristan.api.global.entities.tracking.reproduction.ReproductionModelService;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RutinasService {

    private final RoutineModelService routineModelService;
    private final CategoryModelService categoryModelService;
    private final SequenceModelService sequenceModelService;
    private final RoutineSegmentModelService routineSegmentModelService;
    private final ReproductionModelService reproductionModelService;
    private final StudentModelService studentModelService;

    // * CRUD Operations
    public Routine create(Routine routine) {
        if (routine.getCategory() != null && routine.getCategory().getId() != null) {
            routine.setCategory(categoryModelService.getById(routine.getCategory().getId()));
        }
        
        routine = routineModelService.save(routine);
        
        for (RoutineSegment segment : routine.getSequences()) {
            if (segment.getSequence() != null && segment.getSequence().getId() != null) {
                segment.setSequence(sequenceModelService.getById(segment.getSequence().getId()));
            }
            segment.setRoutine(routine);
            routineSegmentModelService.save(segment);
        }
        
        return routine;
    }

    @Transactional
    public Routine update(Long id, Routine newRoutine) {

        System.out.println("Updating routine with id: " + id);
        System.out.println(newRoutine.getSequences());

        Routine managedRoutine = routineModelService.getById(id);

        managedRoutine.setName(newRoutine.getName());
        if (newRoutine.getDaysOfTheWeek() != null) {
            managedRoutine.setDaysOfTheWeek(newRoutine.getDaysOfTheWeek());
        }

        if (newRoutine.getCategory() != null && newRoutine.getCategory().getId() != null) {
            Category category = categoryModelService.getById(newRoutine.getCategory().getId());
            managedRoutine.setCategory(category);
        }

        managedRoutine.getSequences().removeIf(rs ->
                newRoutine.getSequences().stream()
                        .noneMatch(routineSequence -> Objects.equals(routineSequence.getId(), rs.getId()))
        );

        if (newRoutine.getSequences() != null) {
            for (RoutineSegment incomingRs : newRoutine.getSequences()) {

                if (incomingRs.getSequence() != null && incomingRs.getSequence().getId() != null) {

                    if(incomingRs.getStartTime() == null || incomingRs.getEndTime() == null) {
                        throw new IllegalArgumentException("Start time and end time must be provided for each routine sequence.");
                    }else if(incomingRs.getStartTime().isAfter(incomingRs.getEndTime())) {
                        throw new IllegalArgumentException("Start time must be before end time for each routine sequence.");
                    }

                    if (incomingRs.getId() != null) {
                        managedRoutine.getSequences().stream()
                                .filter(existingRs -> existingRs.getId().equals(incomingRs.getId()))
                                .findFirst()
                                .ifPresent(existingRs -> {
                                    existingRs.setStartTime(incomingRs.getStartTime());
                                    existingRs.setEndTime(incomingRs.getEndTime());

                                    if (!existingRs.getSequence().getId().equals(incomingRs.getSequence().getId())) {
                                        Sequence sequence = sequenceModelService.getById(incomingRs.getSequence().getId());
                                        existingRs.setSequence(sequence);
                                    }
                                });
                    } else {
                        boolean isDuplicate = managedRoutine.getSequences().stream().anyMatch(existingRs ->
                                existingRs.getSequence().getId().equals(incomingRs.getSequence().getId()) &&
                                        java.util.Objects.equals(existingRs.getStartTime(), incomingRs.getStartTime()) &&
                                        java.util.Objects.equals(existingRs.getEndTime(), incomingRs.getEndTime())
                        );

                        if (!isDuplicate) {
                            Sequence sequence = sequenceModelService.getById(incomingRs.getSequence().getId());

                            incomingRs.setSequence(sequence);
                            incomingRs.setRoutine(managedRoutine);

                            managedRoutine.getSequences().add(incomingRs);
                        }
                    }
                }
            }
        }

        return routineModelService.save(managedRoutine);
    }

    public void delete(Long id) {
        List<RoutineSegment> routineSegmentList = routineSegmentModelService.getByRoutineId(id);
        routineSegmentList.forEach(routineSegment -> {
            reproductionModelService.deleteByRoutineSegmentId(routineSegment.getId());
        });

        routineSegmentModelService.deleteByRoutineId(id);

        studentModelService.deleteRoutineAssignmentsByRoutineId(id);

        routineModelService.deleteById(id);
    }

    // * LIST & SEARCH
    public Page<Routine> list(Pageable pageable, String q) {
        return routineModelService.findByTerm(pageable, RoutineSpecification.searchByTerm(q));
    }

    public Routine getById(Long id) {
        return routineModelService.getById(id);
    }

    public List<SortParamDTO> getSortParams() {
        return RoutineModelService.getSortParams();
    }
}



