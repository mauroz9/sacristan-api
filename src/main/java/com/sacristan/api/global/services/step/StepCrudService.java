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
        return stepRepository.findAllBySequenceIdOrderByOrderAsc(sequenceId);
    }

    public Step getStepById(Long id) {
        return stepRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Step not found with id: " + id));
    }

    public Step create(Long sequenceId,Step step){
        Sequence sequence = sequenceRepository.findById(sequenceId)
                .orElseThrow(() -> new BadRequestException("No se puede crear el paso. La secuencia " + sequenceId + " no existe."));

        Integer currentMaxOrder = stepRepository.findMaxOrderBySequenceId(sequenceId);
        step.setPosition(currentMaxOrder + 1);

        // 3. Asignamos la relación y guardamos
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

        stepRepository.delete(step);
    }
}
