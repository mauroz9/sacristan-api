package com.sacristan.api.interfaces.admin.secuencias;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegment;
import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegmentModelService;
import com.sacristan.api.global.entities.content.category.CategoryModelService;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.content.sequence.SequenceModelService;
import com.sacristan.api.global.entities.content.sequence.SequenceSpecification;
import com.sacristan.api.global.entities.content.step.Step;
import com.sacristan.api.global.entities.content.step.StepModelService;
import com.sacristan.api.global.entities.tracking.reproduction.ReproductionModelService;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecuenciasService {

    private final SequenceModelService sequenceModelService;
    private final CategoryModelService categoryModelService;
    private final StepModelService stepModelService;
    private final RoutineSegmentModelService routineSegmentModelService;
    private final ReproductionModelService reproductionModelService;
    private final StudentModelService studentModelService;

    // * CRUD Operations
    public Sequence create(Sequence sequence) {
        // Resolve category from fake ID
        if (sequence.getCategory() != null && sequence.getCategory().getId() != null) {
            sequence.setCategory(categoryModelService.getById(sequence.getCategory().getId()));
        }
        
        sequence = sequenceModelService.save(sequence);
        
        for (Step step : sequence.getSteps()) {
            step.setSequence(sequence);
            stepModelService.save(step);
        }
        
        return sequence;
    }

    public Sequence update(Long id, Sequence newSequence) {
        Sequence sequence = sequenceModelService.getById(id);
        
        if (newSequence.getCategory() != null && newSequence.getCategory().getId() != null) {
            sequence.setCategory(categoryModelService.getById(newSequence.getCategory().getId()));
        }
        sequence.setTitle(newSequence.getTitle());
        sequence.setDescription(newSequence.getDescription());
        sequence.setEstimatedDuration(newSequence.getEstimatedDuration());
        sequence.setAllowGoBack(newSequence.getAllowGoBack() != null ? newSequence.getAllowGoBack() : false);
        sequence.setFrontPage(newSequence.getFrontPage());
        
        sequence.getSteps().clear();
        sequence = sequenceModelService.save(sequence);
        
        for (Step step : newSequence.getSteps()) {
            step.setSequence(sequence);
            stepModelService.save(step);
            sequence.getSteps().add(step);
        }
        
        return sequenceModelService.save(sequence);
    }

    public void delete(Long id) {

        studentModelService.deleteSequenceAssignmentsBySequenceId(id);

        List<RoutineSegment> routineSegmentList = routineSegmentModelService.getBySequenceId(id);
        routineSegmentList.forEach(routineSegment -> {
            reproductionModelService.deleteByRoutineSegmentId(routineSegment.getId());
        });
        routineSegmentModelService.deleteBySequenceId(id);

        stepModelService.deleteBySequenceId(id);
        sequenceModelService.deleteById(id);
    }

    // * LIST & SEARCH
    public Page<Sequence> list(Pageable pageable, String q) {
        return sequenceModelService.findByTerm(pageable, SequenceSpecification.searchByTerm(q));
    }

    public Sequence getById(Long id) {
        return sequenceModelService.getById(id);
    }

    public List<SortParamDTO> getSortParams() {
        return SequenceModelService.getSortParams();
    }

    // * SPECIAL Operations
    public Sequence duplicate(Long id) {
        Sequence originalSequence = sequenceModelService.getById(id);
        Sequence duplicatedSequence = originalSequence.duplicate();
        
        duplicatedSequence = sequenceModelService.save(duplicatedSequence);
        
        for (Step originalStep : originalSequence.getSteps()) {
            Step duplicatedStep = originalStep.duplicate(duplicatedSequence);
            stepModelService.save(duplicatedStep);
        }
        
        return sequenceModelService.getById(duplicatedSequence.getId());
    }
}
