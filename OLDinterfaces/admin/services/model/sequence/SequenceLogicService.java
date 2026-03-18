package com.sacristan.api.OLDinterfaces.admin.services.model.sequence;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.repositories.SequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SequenceLogicService {

    private final SequenceCrudService crudService;
    private final SequenceRepository repository;

    public Sequence duplicate(Long id) {
        Sequence originalSequence = crudService.read(id);

        Sequence duplicatedSequence = originalSequence.duplicate();

        duplicatedSequence.setSteps(originalSequence.getSteps().stream().map(step -> step.duplicate(duplicatedSequence)).toList());

        return repository.save(duplicatedSequence);
    }

    public List<SortParamDTO> getSequenceSortParams() {
        return List.of(
                new SortParamDTO("Título" , "title"),
                new SortParamDTO("Descripción", "description"),
                new SortParamDTO("Categoría", "category.name")
        );
    }


}
