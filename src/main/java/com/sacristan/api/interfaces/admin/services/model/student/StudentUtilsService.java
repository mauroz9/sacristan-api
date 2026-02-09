package com.sacristan.api.interfaces.admin.services.model.student;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentUtilsService {

    private final StudentRepository repository;


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
}
