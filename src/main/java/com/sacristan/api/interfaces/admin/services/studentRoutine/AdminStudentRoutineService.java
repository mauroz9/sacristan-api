
package com.sacristan.api.interfaces.admin.services.studentRoutine;

import com.sacristan.api.global.models.Routine;
import com.sacristan.api.global.models.RoutineSequence;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.repositories.RoutineRepository;
import com.sacristan.api.global.repositories.SequenceRepository;
import com.sacristan.api.global.repositories.StudentRepository;
import io.micrometer.common.KeyValues;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminStudentRoutineService {

    private final StudentRepository studentRepository;
    private final RoutineRepository routineRepository;

    public Student assignRoutineToStudent(Long studentId, Long routineId) {
        Student s = studentRepository.findById(studentId).orElseThrow(
                () -> new NoSuchElementException("Student with id " + studentId + " not found")
        );

        Routine r = routineRepository.findById(routineId).orElseThrow(
                () -> new NoSuchElementException("Routine with id " + routineId + " not found")
        );

        Set<Sequence> sequencesRoutine = r.getSequences()
                .stream()
                .map(RoutineSequence::getSequence)
                .filter(seq -> !s.getSequences().contains(seq))
                .collect(Collectors.toSet());

        s.getSequences().addAll(sequencesRoutine);
        s.getRoutines().add(r);
        return studentRepository.save(s);


    }

    public Student unassignRoutineFromStudent(Long studentId, Long routineId) {

        Student s = studentRepository.findById(studentId).orElseThrow(
                () -> new NoSuchElementException("Student with id " + studentId + " not found")
        );

        Routine r = routineRepository.findById(routineId).orElseThrow(
                () -> new NoSuchElementException("Routine with id " + routineId + " not found")
        );

        if (!s.getRoutines().contains(r)) {
            throw new IllegalStateException("Routine with id " + routineId + " is not assigned to student with id " + studentId);
        }

        s.getRoutines().remove(r);
        return studentRepository.save(s);
    }

    public Set<Routine> getRoutinesOfStudent(Long studentId) {
        Student s = studentRepository.findById(studentId).orElseThrow(
                () -> new NoSuchElementException("Student with id " + studentId + " not found")
        );
        return s.getRoutines();
    }

    public Set<Routine> getUnassignedRoutinesOfStudent(Long studentId) {
        Student s = studentRepository.findById(studentId).orElseThrow(
                () -> new NoSuchElementException("Student with id " + studentId + " not found")
        );
        return routineRepository.findAll()
                .stream()
                .filter(r -> !s.getRoutines().contains(r))
                .collect(Collectors.toSet());
    }
}
