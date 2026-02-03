package com.sacristan.api.interfaces.admin.services.reproduction;

import com.sacristan.api.global.models.Reproduction;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.repositories.ReproductionRepository;
import com.sacristan.api.global.repositories.SequenceRepository;
import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReproductionCrudService {

    private final ReproductionRepository repository;
    private final SequenceRepository sequenceRepository;
    private final StudentRepository studentRepository;

    public Page<Reproduction> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Reproduction read(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reproduction not found with id: " + id));
    }

    public Reproduction create(Reproduction reproduction) {
        // Validar que la secuencia existe
        if (reproduction.getSequence() != null && reproduction.getSequence().getId() != null) {
            Sequence sequence = sequenceRepository.findById(reproduction.getSequence().getId())
                    .orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + reproduction.getSequence().getId()));
            reproduction.setSequence(sequence);
        }

        // Validar que el estudiante existe
        if (reproduction.getStudent() != null && reproduction.getStudent().getId() != null) {
            Student student = studentRepository.findById(reproduction.getStudent().getId())
                    .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + reproduction.getStudent().getId()));
            reproduction.setStudent(student);
        }

        return repository.save(reproduction);
    }

    @Transactional
    public Reproduction update(Long id, Reproduction newReproduction) {
        Reproduction reproduction = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reproduction not found with id: " + id));

        // Validar que la secuencia existe
        if (newReproduction.getSequence() != null && newReproduction.getSequence().getId() != null) {
            Sequence sequence = sequenceRepository.findById(newReproduction.getSequence().getId())
                    .orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + newReproduction.getSequence().getId()));
            newReproduction.setSequence(sequence);
        }

        // Validar que el estudiante existe
        if (newReproduction.getStudent() != null && newReproduction.getStudent().getId() != null) {
            Student student = studentRepository.findById(newReproduction.getStudent().getId())
                    .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + newReproduction.getStudent().getId()));
            newReproduction.setStudent(student);
        }

        Reproduction modifiedReproduction = reproduction.modify(newReproduction);
        return repository.save(modifiedReproduction);
    }

    public void delete(Long id) {
        Reproduction reproduction = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reproduction not found with id: " + id));
        repository.delete(reproduction);
    }
}
