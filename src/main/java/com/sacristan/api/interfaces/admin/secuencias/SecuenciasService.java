package com.sacristan.api.interfaces.admin.secuencias;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.content.category.Category;
import com.sacristan.api.global.entities.content.category.CategoryModelService;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.content.sequence.SequenceModelService;
import com.sacristan.api.global.entities.content.sequence.SequenceSpecification;
import com.sacristan.api.global.entities.content.step.Step;
import com.sacristan.api.global.entities.content.step.StepModelService;
import com.sacristan.api.interfaces.admin.secuencias.dtos.request.CreateSequenceRequest;
import com.sacristan.api.interfaces.admin.secuencias.dtos.request.UpdateSequenceRequest;
import com.sacristan.api.interfaces.admin.secuencias.dtos.request.UpdateStepRequest;
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

    // * CRUD Operations
    public Sequence create(CreateSequenceRequest createRequest) {
        Category category = categoryModelService.getById(createRequest.categoryId());
        Sequence sequence = createRequest.toEntity(category);
        sequence = sequenceModelService.save(sequence);
        
        for (Step step : sequence.getSteps()) {
            step.setSequence(sequence);
            stepModelService.save(step);
        }
        
        return sequence;
    }

    public Sequence update(Long id, UpdateSequenceRequest updateRequest) {
        Sequence sequence = sequenceModelService.getById(id);
        Category category = categoryModelService.getById(updateRequest.categoryId());
        
        sequence.setTitle(updateRequest.title());
        sequence.setDescription(updateRequest.description());
        sequence.setCategory(category);
        sequence.setEstimatedDuration(updateRequest.estimatedDuration());
        sequence.setAllowGoBack(updateRequest.allowGoBack() != null ? updateRequest.allowGoBack() : false);
        sequence.setFrontPage(updateRequest.frontPage());
        
        sequence.getSteps().clear();
        sequence = sequenceModelService.save(sequence);
        
        for (UpdateStepRequest stepRequest : updateRequest.steps()) {
            Step step = stepRequest.toEntity();
            step.setSequence(sequence);
            stepModelService.save(step);
            sequence.getSteps().add(step);
        }
        
        return sequenceModelService.save(sequence);
    }

    public void delete(Long id) {
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
