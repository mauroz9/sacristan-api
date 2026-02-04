package com.sacristan.api.interfaces.admin.services.sequence;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.Step;
import com.sacristan.api.global.repositories.SequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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


}
