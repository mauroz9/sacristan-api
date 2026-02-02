package com.sacristan.api.global.services.sequence;

import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.repositories.CategoryRepository;
import com.sacristan.api.global.repositories.SequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SequenceCrudService {

    private final SequenceRepository repository;
    private final CategoryRepository categoryRepository;


    public List<Sequence> getAll() {
        return repository.findAll();
    }

    public Sequence read(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + id));
    }

    public Sequence create(Sequence sequence) {

        if (sequence.getCategory() != null && sequence.getCategory().getId() != null) {
            Category category = categoryRepository.findById(sequence.getCategory().getId())
                    .orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + sequence.getCategory().getId()));
            sequence.setCategory(category);
        }

        return repository.save(sequence);
    }

    public Sequence update(Long id, Sequence newSequence){
        Sequence sequence = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + id));

        return repository.save(sequence.modify(newSequence));
    }

    public void delete(Long id) {
        Sequence sequence = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + id));
        repository.delete(sequence);
    }
}
