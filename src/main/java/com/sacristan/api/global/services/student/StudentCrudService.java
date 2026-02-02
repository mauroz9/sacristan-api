package com.sacristan.api.global.services.student;

import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.models.user.extra.Role;
import com.sacristan.api.global.repositories.StudentRepository;
import com.sacristan.api.global.services.user.UserCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentCrudService {

    private final StudentRepository repository;
    private final UserCrudService userCrudService;

    public Student create(User user) {

        User newUser = userCrudService.create(user);
        newUser.setRole(Role.STUDENT);

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
        repository.deleteById(id);
    }

    public Page<Student> list(Pageable pageable) {

        Page<Student> students = repository.findAll(pageable);

        if (students.isEmpty())
            throw new NoSuchElementException("No students found");

        return students;
    }

}
