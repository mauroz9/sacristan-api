package com.sacristan.api.interfaces.admin.services.model.student;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.models.Routine;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.repositories.RoutineRepository;
import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentUtilsService {

    private final StudentRepository repository;
    private final RoutineRepository routineRepository;


    public List<Student> findByTeacher(Teacher teacher) {
        return repository.findByTeacher(teacher);
    }

    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    public Student save(Student s) {
        return repository.save(s);
    }

    public Integer getStudentCountByTeacherId(Long id) {
        return repository.getStudentCountByTeacherId(id);
    }

    public List<SortParamDTO> getStudentSortParams() {
        return List.of(
                new SortParamDTO("Nombre" , "user.name"),
                new SortParamDTO("Apellidos", "user.lastName"),
                new SortParamDTO("Email", "user.email"),
                new SortParamDTO("Usuario", "user.username")
        );
    }

    public Student assignRoutine(Long idRoutine, Long idStudent) {
        Routine routine = routineRepository.findById(idRoutine).orElseThrow(
                () -> new NoSuchElementException("Routine with id " + idRoutine + " not found")
        );

        Student student = repository.findById(idStudent).orElseThrow(
                () -> new NoSuchElementException("Student with id " + idStudent + " not found")
        );

        Set<Sequence> studentSequences = student.getSequences();
        Set<Sequence> routineSequences = routine.getSequences()
                .stream()
                .map(rs -> rs.getSequence())
                .filter(s -> !studentSequences.contains(s))
                .collect(java.util.stream.Collectors.toSet());

        studentSequences.addAll(routineSequences);

        student.getRoutines().add(routine);

        return student;

    }

    public Student unassignRoutine(Long idRoutine, Long idStudent) {
        Routine routine = routineRepository.findById(idRoutine).orElseThrow(
                () -> new NoSuchElementException("Routine with id " + idRoutine + " not found")
        );

        Student student = repository.findById(idStudent).orElseThrow(
                () -> new NoSuchElementException("Student with id " + idStudent + " not found")
        );

        student.getRoutines().remove(routine);

        return student;
    }
}
