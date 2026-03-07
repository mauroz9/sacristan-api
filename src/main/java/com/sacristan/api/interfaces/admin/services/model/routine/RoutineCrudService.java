package com.sacristan.api.interfaces.admin.services.model.routine;

import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.Routine;
import com.sacristan.api.global.models.RoutineSequence;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.repositories.*;
import com.sacristan.api.global.specifications.RoutineSpecification;
import com.sacristan.api.global.specifications.StudentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoutineCrudService {

    private final RoutineRepository repository;
    private final CategoryRepository categoryRepository;
    private final SequenceRepository sequenceRepository;
    private final StudentRepository studentRepository;

    public Page<Routine> list(Pageable pageable, String q) {
        return repository.findBy(RoutineSpecification.searchByTerm(q), p -> p.page(pageable));

    }

    public Routine read(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Routine not found with id: " + id));
    }

    public Routine create(Routine routine) {
        if (routine.getCategory() != null && routine.getCategory().getId() != null) {
            Category category = categoryRepository.findById(routine.getCategory().getId())
                    .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + routine.getCategory().getId()));
            routine.setCategory(category);
        }

        if (routine.getSequences() != null && !routine.getSequences().isEmpty()) {
            routine.getSequences().forEach(routineSequence -> {
                // Validar que la secuencia existe en la base de datos
                if (routineSequence.getSequence() != null && routineSequence.getSequence().getId() != null) {
                    Sequence sequence = sequenceRepository.findById(routineSequence.getSequence().getId())
                            .orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + routineSequence.getSequence().getId()));
                    routineSequence.setSequence(sequence);
                }
                routineSequence.setRoutine(routine);
            });
        }

        return repository.save(routine);
    }

    @Transactional
    public Routine update(Long id, Routine newRoutine) {

        System.out.println("Updating routine with id: " + id);
        System.out.println(newRoutine.getSequences());

        Routine managedRoutine = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Routine not found with id: " + id));

        managedRoutine.setName(newRoutine.getName());
        if (newRoutine.getDaysOfTheWeek() != null) {
            managedRoutine.setDaysOfTheWeek(newRoutine.getDaysOfTheWeek());
        }

        if (newRoutine.getCategory() != null && newRoutine.getCategory().getId() != null) {
            Category category = categoryRepository.findById(newRoutine.getCategory().getId())
                    .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + newRoutine.getCategory().getId()));
            managedRoutine.setCategory(category);
        }

        if (newRoutine.getSequences() != null) {
            for (RoutineSequence incomingRs : newRoutine.getSequences()) {

                if (incomingRs.getSequence() != null && incomingRs.getSequence().getId() != null) {

                    if (incomingRs.getId() != null) {
                        managedRoutine.getSequences().stream()
                                .filter(existingRs -> existingRs.getId().equals(incomingRs.getId()))
                                .findFirst()
                                .ifPresent(existingRs -> {
                                    existingRs.setStartTime(incomingRs.getStartTime());
                                    existingRs.setEndTime(incomingRs.getEndTime());

                                    if (!existingRs.getSequence().getId().equals(incomingRs.getSequence().getId())) {
                                        Sequence sequence = sequenceRepository.findById(incomingRs.getSequence().getId())
                                                .orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + incomingRs.getSequence().getId()));
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
                            Sequence sequence = sequenceRepository.findById(incomingRs.getSequence().getId())
                                    .orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + incomingRs.getSequence().getId()));

                            incomingRs.setSequence(sequence);
                            incomingRs.setRoutine(managedRoutine);

                            managedRoutine.getSequences().add(incomingRs);
                        }
                    }
                }
            }
        }

        return repository.save(managedRoutine);
    }

    @Transactional
    public void delete(Long id) {
        Routine routine = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Routine not found with id: " + id));

        studentRepository.deleteRoutineAssociationsByRoutineId(id);

        repository.delete(routine);
    }
}
