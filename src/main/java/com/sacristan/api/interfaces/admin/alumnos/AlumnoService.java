package com.sacristan.api.interfaces.admin.alumnos;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.global.entities.content.rotuine.RoutineModelService;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.content.sequence.SequenceModelService;
import com.sacristan.api.global.entities.tracking.reproduction.ReproductionModelService;
import com.sacristan.api.global.entities.users.role.Role;
import com.sacristan.api.global.entities.users.student.Student;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import com.sacristan.api.global.entities.users.student.StudentSpecification;
import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.entities.users.user.UserModelService;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.dashboard.*;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.reproduction.ReproductionDetailDTO;
import com.sacristan.api.interfaces.shared.services.StudentStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final StudentModelService studentModelService;
    private final UserModelService userModelService;
    private final SequenceModelService sequenceModelService;
    private final RoutineModelService routineModelService;
    private final ReproductionModelService reproductionModelService;
    private final StudentStatsService studentStatsService;

    public void create(Student student) {
        User createdUser = userModelService.create(student.getUser(), Role.STUDENT);
        student.setUser(createdUser);
        studentModelService.save(student);
    }

    public void update(Long id, User user) {
        userModelService.update(id, user);
    }

    public void delete(Long id) {
        reproductionModelService.deleteByStudentId(id);
        studentModelService.deleteById(id);
        userModelService.deleteById(id);
    }

    public Student getById(Long id) {
        return studentModelService.getById(id);
    }

    public Page<Student> list(Pageable pageable, String q) {
        return studentModelService.findByTerm(pageable, StudentSpecification.searchByTerm(q));
    }

    public List<SortParamDTO> getSortParams() {
        return StudentModelService.getSortParams();
    }

    public Student assignSequenceToStudent(Long studentId, Long sequenceId) {
        Student student = studentModelService.getById(studentId);
        Sequence sequence = sequenceModelService.getById(sequenceId);

        if (student.getSequences().contains(sequence)) {
            throw new IllegalArgumentException("Sequence with id: " + sequenceId + " is already assigned to student with id: " + studentId);
        }

        student.assignSequence(sequence);
        return studentModelService.save(student);
    }

    public Student unassignSequenceFromStudent(Long studentId, Long sequenceId) {
        Student student = studentModelService.getById(studentId);
        Sequence sequence = sequenceModelService.getById(sequenceId);

        if (!student.getSequences().contains(sequence)) {
            throw new IllegalArgumentException("Sequence with id: " + sequenceId + " is not assigned to student with id: " + studentId);
        }

        student.unassignSequence(sequence);
        return studentModelService.save(student);
    }

    public Set<Sequence> getSequencesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        return student.getSequences();
    }

    public List<Sequence> getUnassignedSequencesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        try {
            List<Sequence> allSequences = sequenceModelService.listAll();
            allSequences.removeAll(student.getSequences());
            return allSequences;
        } catch (Exception e) {
            return List.of();
        }
    }

    public Integer countAssignedSequencesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        return student.getSequences().size();
    }

    public Student assignRoutineToStudent(Long studentId, Long routineId) {
        Student student = studentModelService.getById(studentId);
        Routine routine = routineModelService.getById(routineId);

        if (student.getRoutines().contains(routine)) {
            throw new IllegalArgumentException("Routine with id: " + routineId + " is already assigned to student with id: " + studentId);
        }

        student.getRoutines().add(routine);
        return studentModelService.save(student);
    }

    public Student unassignRoutineFromStudent(Long studentId, Long routineId) {
        Student student = studentModelService.getById(studentId);
        Routine routine = routineModelService.getById(routineId);

        if (!student.getRoutines().contains(routine)) {
            throw new IllegalArgumentException("Routine with id: " + routineId + " is not assigned to student with id: " + studentId);
        }

        student.getRoutines().remove(routine);
        return studentModelService.save(student);
    }

    public Set<Routine> getRoutinesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        return student.getRoutines();
    }

    public Set<Routine> getUnassignedRoutinesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        try {
            return routineModelService.listAll()
                    .stream()
                    .filter(r -> !student.getRoutines().contains(r))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            return Set.of();
        }
    }

    public Integer countAssignedRoutinesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        return student.getRoutines().size();
    }

    // ==================== Stats Methods (delegados a StudentStatsService) ====================

    public StudentStatsDTO getStudentStats(Long studentId) {
        return studentStatsService.calculateStudentStats(studentId);
    }

    public List<DailyProgressDTO> getWeeklyProgress(Long studentId) {
        return studentStatsService.getWeeklyProgress(studentId);
    }

    public List<CategoryStatDTO> getCategoryStats(Long studentId) {
        return studentStatsService.getCategoryStats(studentId);
    }

    public Page<AssignedSequenceProgressDTO> getStudentAgenda(Long studentId, Pageable pageable) {
        return studentStatsService.getStudentAgenda(studentId, pageable);
    }

    public Page<RecentActivityDTO> getStudentActivity(Long studentId, Pageable pageable) {
        return studentStatsService.getStudentActivity(studentId, pageable);
    }

    public ReproductionDetailDTO getReproductionDetail(Long reproductionId) {
        return studentStatsService.getReproductionDetail(reproductionId);
    }
}

