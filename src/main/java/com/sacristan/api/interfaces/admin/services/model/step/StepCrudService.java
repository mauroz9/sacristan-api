package com.sacristan.api.interfaces.admin.services.model.step;

import com.sacristan.api.global.error.exceptions.BadRequestException;
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

    public List<Step> getAll() {
        return stepRepository.findAll();
    }

    public List<Step> getStepsBySequence(Long sequenceId) {
        if (!sequenceRepository.existsById(sequenceId)) {
            throw new BadRequestException("Sequence not found with id: " + sequenceId);
        }
        return stepRepository.findAllBySequenceIdOrderByPositionAsc(sequenceId);
    }

    public Step getStepById(Long id) {
        return stepRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Step not found with id: " + id));
    }

    public Step create(Step step) {
        if (step.getSequence() != null && step.getSequence().getId() != null) {
            Sequence sequence = sequenceRepository.findById(step.getSequence().getId())
                    .orElseThrow(() -> new BadRequestException("Sequence not found with id: " + step.getSequence().getId()));
            step.setSequence(sequence);
        }

        return stepRepository.save(step);
    }

    public Step update(Long id, Step newStep) {
        Step step = stepRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Step not found with id: " + id));

        if (newStep.getSequence() != null && newStep.getSequence().getId() != null) {
            Sequence sequence = sequenceRepository.findById(newStep.getSequence().getId())
                    .orElseThrow(() -> new BadRequestException("Sequence not found with id: " + newStep.getSequence().getId()));
            newStep.setSequence(sequence);
        }

        Step modifiedStep = step.modify(newStep);
        if (newStep.getSequence() != null) {
            modifiedStep.setSequence(newStep.getSequence());
        }

        return stepRepository.save(modifiedStep);
    }

    public void delete(Long id) {
        Step step = stepRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Step not found with id: " + id));
        stepRepository.delete(step);
    }
}
