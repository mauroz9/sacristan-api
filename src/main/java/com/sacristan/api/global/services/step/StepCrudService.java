package com.sacristan.api.global.services.step;

import com.sacristan.api.error.exceptions.BadRequestException;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.Step;
import com.sacristan.api.global.repositories.SequenceRepository;
import com.sacristan.api.global.repositories.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StepCrudService {

    private final StepRepository stepRepository;
    private final SequenceRepository sequenceRepository;

    public List<Step> getStepsBySequence(Long sequenceId) {
        if (!sequenceRepository.existsById(sequenceId)) {
            throw new BadRequestException("Sequence with id " + sequenceId + " does not exists.");
        }
        return stepRepository.findAllBySequenceIdOrderByPositionAsc(sequenceId);
    }

    public Step getStepById(Long id) {
        return stepRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Step not found with id: " + id));
    }

    public Step create(Long sequenceId,Step step){
        Sequence sequence = sequenceRepository.findById(sequenceId)
                .orElseThrow(() -> new BadRequestException("Could not create the step. Sequence " + sequenceId + " does not exists."));

        Integer currentMaxOrder = stepRepository.findMaxOrderBySequenceId(sequenceId);
        step.setPosition(currentMaxOrder + 1);

        step.setSequence(sequence);

        return stepRepository.save(step);
    }

    public Step update(Long id, Step newStep){
        Step step = stepRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Step not found with id: " + id));

        return stepRepository.save(step.modify(newStep));
    }

    public void delete(Long id){
        Step step = stepRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Step not found with id: " + id));

        Long sequenceId = step.getSequence().getId();
        stepRepository.delete(step);

        List<Step> remainingSteps = stepRepository.findAllBySequenceIdOrderByOrderAsc(sequenceId);
        int position = 1;
        for (Step remainingStep : remainingSteps) {
            remainingStep.setPosition(position++);
        }
        stepRepository.saveAll(remainingSteps);
    }
}
