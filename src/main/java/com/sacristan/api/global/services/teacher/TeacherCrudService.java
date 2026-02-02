package com.sacristan.api.global.services.teacher;

import com.sacristan.api.error.BadRequestException;
import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.TeacherRepository;
import com.sacristan.api.global.services.user.UserCrudService;
import com.sacristan.api.global.services.user.UserUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TeacherCrudService {

    private final TeacherRepository repository;

    private final UserUtilsService userUtilsService;

    public Teacher create(User user) {

        return repository.save(Teacher.builder()
                .user(userUtilsService.getReferenceById(user.getId()))
                .build());

    }

    public Teacher read(long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Teacher not found with id: " + id));
    }

    public void delete(long id) {
        Teacher teacher = repository.findById(id).orElseThrow(()-> new NoSuchElementException("Teacher not found with id: " + id));

        if (!teacher.getStudents().isEmpty())
            throw new BadRequestException("Cannot delete teacher with assigned students");

        repository.delete(teacher);
    }

    public Page<Teacher> list(Pageable pageable) {

        Page<Teacher> teachers = repository.findAll(pageable);

        if (teachers.isEmpty())
            throw new NoSuchElementException("No teachers found");

        return teachers;
    }

}
