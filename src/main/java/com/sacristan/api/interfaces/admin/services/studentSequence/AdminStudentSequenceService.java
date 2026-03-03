package com.sacristan.api.interfaces.admin.services.studentSequence;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.repositories.SequenceRepository;
import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminStudentSequenceService {

    private final StudentRepository studentRepository;
    private final SequenceRepository sequenceRepository;

    public Student assignSequenceToStudent(Long studentId, Long sequenceId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));

        Sequence sequence = sequenceRepository.findById(sequenceId)
                .orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + sequenceId));

        if(student.getSequences().contains(sequence)) {
            throw new IllegalStateException("Sequence with id: " + sequenceId + " is already assigned to student with id: " + studentId);
        }

        student.assignSequence(sequence);
        return studentRepository.save(student);
    }

    public Student unassignSequenceFromStudent(Long studentId, Long sequenceId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));

        Sequence sequence = sequenceRepository.findById(sequenceId)
                .orElseThrow(() -> new NoSuchElementException("Sequence not found with id: " + sequenceId));

        if(!student.getSequences().contains(sequence)) {
            throw new IllegalStateException("Sequence with id: " + sequenceId + " is not assigned to student with id: " + studentId);
        }

        student.unassignSequence(sequence);
        return studentRepository.save(student);
    }

    public Set<Sequence> getSequencesOfStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));

        return student.getSequences();
    }

    public List<Sequence> getUnassignedSequencesOfStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));

        List<Sequence> allSequences = sequenceRepository.findAll();
        allSequences.removeAll(student.getSequences());
        return allSequences;
    }

    public Integer countAssignedSequencesOfStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + studentId));

        return studentRepository.countSequencesByStudentId(studentId);
    }
}
