package com.sacristan.api.interfaces.admin.services.student;

import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.models.user.extra.Role;
import com.sacristan.api.global.repositories.StudentRepository;
import com.sacristan.api.interfaces.admin.services.user.UserCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentCrudService {

    private final StudentRepository repository;
    private final UserCrudService userCrudService;

    public Student create(User user) {

        User newUser = userCrudService.create(user);
        newUser.setRoles(Set.of(Role.STUDENT));

        return repository.save(Student.builder()
                .user(newUser)
                .build());
    }

    public Student read(long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + id));
    }

    public void delete(long id) {
        Student student = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Student not found with id: " + id));
        student.getTeacher().removeStudent(student);
        repository.delete(student);
    }

    public Page<Student> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
