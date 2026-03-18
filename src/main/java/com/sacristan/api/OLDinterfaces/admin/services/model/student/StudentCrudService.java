package com.sacristan.api.OLDinterfaces.admin.services.model.student;

import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.models.user.extra.Role;
import com.sacristan.api.global.repositories.StudentRepository;
import com.sacristan.api.global.specifications.StudentSpecification;
import com.sacristan.api.interfaces.admin.services.model.user.UserCrudService;
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

    public User update(Long id, User user) {
        return userCrudService.update(id, user);
    }

    public void delete(long id) {
        Student student = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Student not found with id: " + id));

        if (student.getTeacher() != null)
            student.setTeacher(null);

        student.getSequences().clear();

        repository.delete(student);
    }

    public Page<Student> list(Pageable pageable, String q) {
        return repository.findBy(StudentSpecification.searchByTerm(q), p -> p.page(pageable));
    }


}
