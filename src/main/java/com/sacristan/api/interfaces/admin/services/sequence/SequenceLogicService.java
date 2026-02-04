package com.sacristan.api.interfaces.admin.services.sequence;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.Step;
import com.sacristan.api.global.repositories.SequenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SequenceLogicService {

    private final SequenceCrudService crudService;
    private final SequenceRepository repository;

    public Sequence duplicate(Long id) {
        Sequence originalSequence = crudService.read(id);

        Sequence duplicatedSequence = Sequence.builder()
                .title(originalSequence.getTitle() + " (Copy)")
                .description(originalSequence.getDescription())
                .estimatedDuration(originalSequence.getEstimatedDuration())
                .allowGoBack(originalSequence.getAllowGoBack())
                .category(originalSequence.getCategory())
                .build();

        List<Step> duplicatedSteps = originalSequence.getSteps().stream()
                .map(step -> Step.builder()
                        .name(step.getName())
                        .position(step.getPosition())
                        .estimatedDuration(step.getEstimatedDuration())
                        .arasaacPictogramId(step.getArasaacPictogramId())
                        .sequence(duplicatedSequence)
                        .build())
                .collect(Collectors.toList());

        duplicatedSequence.setSteps(duplicatedSteps);

        return repository.save(duplicatedSequence);
    }


}
