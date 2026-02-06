package com.sacristan.api.interfaces.admin.services.model.teacher;

import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TeacherUtilsService {

    private final TeacherRepository repository;

    public Teacher getReferenceById(Long id) {
        return repository.getReferenceById(id);
    }

    public Optional<Teacher> findById(Long idTeacher) {
        return repository.findById(idTeacher);
    }

    public Teacher save(Teacher teacher) {
        return repository.save(teacher);
    }

}
