package com.sacristan.api.interfaces.admin.services.model.routine;

import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.Routine;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoutineCrudService {

    private final RoutineRepository repository;
    private final CategoryRepository categoryRepository;
    private final SequenceRepository sequenceRepository;
    private final StudentRepository studentRepository;

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

        if (routine.getSequences() != null && !routine.getSequences().isEmpty()) {
            routine.getSequences().forEach(routineSequence -> {
                // Validar que la secuencia existe en la base de datos
                if (routineSequence.getSequence() != null && routineSequence.getSequence().getId() != null) {
                    Sequence sequence = sequenceRepository.findById(routineSequence.getSequence().getId())
                            .orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + routineSequence.getSequence().getId()));
                    routineSequence.setSequence(sequence);
                }
                routineSequence.setRoutine(routine);
            });
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
            modifiedRoutine.getSequences().forEach(routineSequence -> {
                // Validar que la secuencia existe en la base de datos
                if (routineSequence.getSequence() != null && routineSequence.getSequence().getId() != null) {
                    Sequence sequence = sequenceRepository.findById(routineSequence.getSequence().getId())
                            .orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + routineSequence.getSequence().getId()));
                    routineSequence.setSequence(sequence);
                }
                routineSequence.setRoutine(modifiedRoutine);
            });
        }
        return repository.save(modifiedRoutine);
    }

    public void delete(Long id) {
        Routine routine = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Routine not found with id: " + id));

        List<Student> studentsWithRoutine = studentRepository.findByRoutinesId(id);

        for (Student student : studentsWithRoutine) {
            student.getRoutines().remove(routine);
            studentRepository.save(student);
        }

        repository.delete(routine);
    }
}
