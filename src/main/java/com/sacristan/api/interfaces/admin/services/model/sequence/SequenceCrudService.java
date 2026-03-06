package com.sacristan.api.interfaces.admin.services.model.sequence;

import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.RoutineSequence;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.repositories.*;
import com.sacristan.api.global.specifications.SequenceSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SequenceCrudService {

    private final SequenceRepository repository;
    private final CategoryRepository categoryRepository;
    private final ReproductionRepository reproductionRepository;
    private final RoutineSequenceRepository routineSequenceRepository;
    private final StudentRepository studentRepository;

    public Page<Sequence> getAll(Pageable pageable, String q) {
        return repository.findBy(SequenceSpecification.searchByTerm(q), p -> p.page(pageable));
    }

    public Sequence read(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + id));
    }

    public Sequence create(Sequence sequence) {
        if (sequence.getCategory() != null && sequence.getCategory().getId() != null) {
            Category category = categoryRepository.findById(sequence.getCategory().getId())
                    .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + sequence.getCategory().getId()));
            sequence.setCategory(category);
        }

        return repository.save(sequence);
    }

    @Transactional
    public Sequence update(Long id, Sequence newSequence){
        Sequence sequence = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + id));

        if (newSequence.getCategory() != null && newSequence.getCategory().getId() != null) {
            Category category = categoryRepository.findById(newSequence.getCategory().getId())
                    .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + newSequence.getCategory().getId()));
            newSequence.setCategory(category);
        }

        sequence.getSteps().clear();
        repository.flush();

        Sequence modifiedSequence = sequence.modify(newSequence);

        if (modifiedSequence.getSteps() != null && !modifiedSequence.getSteps().isEmpty()) {
            modifiedSequence.getSteps().forEach(step -> step.setSequence(modifiedSequence));
        }
        return repository.save(modifiedSequence);
    }


    @Transactional
    public void delete(Long id) {
        Sequence sequence = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + id));

        studentRepository.deleteSequenceFromStudents(sequence.getId());
        reproductionRepository.deleteByRoutineSequenceId(sequence.getId());
        routineSequenceRepository.deleteBySequenceId(sequence.getId());

        repository.delete(sequence);
    }
}

