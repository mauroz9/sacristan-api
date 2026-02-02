package com.sacristan.api.global.services.category;

import com.sacristan.api.error.BadRequestException;
import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.repositories.CategoryRepository;
import com.sacristan.api.global.repositories.RoutineRepository;
import com.sacristan.api.global.repositories.SequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryCrudService {

    private final CategoryRepository repository;
    private final SequenceRepository sequenceRepository;
    private final RoutineRepository routineRepository;

    public Category create(Category category) {

        return repository.save(category);
    }

    public Category read(long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));
    }

    public void delete(long id) {
        Category category = repository.findById(id).orElseThrow(()-> new NoSuchElementException("Category not found with id: " + id));

        if (sequenceRepository.existsByCategory(category) || routineRepository.existsByCategory(category))
            throw new BadRequestException("Cannot delete Category with assigned sequences or routines");

        repository.delete(category);
    }

    public Page<Category> list(Pageable pageable) {

        Page<Category> categories = repository.findAll(pageable);

        if (categories.isEmpty())
            throw new NoSuchElementException("No Categories found");

        return categories;
    }

    public Category update(Long id, Category newCategory){
        Category category = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));

        return repository.save(category.modify(newCategory));
    }

}
