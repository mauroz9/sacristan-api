package com.sacristan.api.global.services.routine;

import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.Routine;
import com.sacristan.api.global.repositories.CategoryRepository;
import com.sacristan.api.global.repositories.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoutineCrudService {

    private final RoutineRepository repository;
    private final CategoryRepository categoryRepository;

    public Page<Routine> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Routine read(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Routine not found with id: " + id));
    }

    public Routine create(Routine routine) {
        if (routine.getCategory() != null && routine.getCategory().getId() != null) {
            Category category = categoryRepository.findById(routine.getCategory().getId())
                    .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + routine.getCategory().getId()));
            routine.setCategory(category);
        }

        return repository.save(routine);
    }

    @Transactional
    public Routine update(Long id, Routine newRoutine) {
        Routine routine = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Routine not found with id: " + id));

        if (newRoutine.getCategory() != null && newRoutine.getCategory().getId() != null) {
            Category category = categoryRepository.findById(newRoutine.getCategory().getId())
                    .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + newRoutine.getCategory().getId()));
            newRoutine.setCategory(category);
        }

        routine.getSequences().clear();
        repository.flush();

        Routine modifiedRoutine = routine.modify(newRoutine);

        if (modifiedRoutine.getSequences() != null && !modifiedRoutine.getSequences().isEmpty()) {
            modifiedRoutine.getSequences().forEach(routineSequence -> routineSequence.setRoutine(modifiedRoutine));
        }
        return repository.save(modifiedRoutine);
    }

    public void delete(Long id) {
        Routine routine = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Routine not found with id: " + id));
        repository.delete(routine);
    }
}
