package com.sacristan.api.interfaces.admin.services.model.teacher;

import com.sacristan.api.global.error.exceptions.BadRequestException;
import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.models.user.extra.Role;
import com.sacristan.api.global.repositories.TeacherRepository;
import com.sacristan.api.global.spefications.StudentSpecification;
import com.sacristan.api.global.spefications.TeacherSpecification;
import com.sacristan.api.interfaces.admin.services.model.user.UserCrudService;
import com.sacristan.api.interfaces.admin.services.model.user.UserUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherCrudService {

    private final TeacherRepository repository;
    private final UserCrudService userCrudService;
    private final UserUtilsService userUtilsService;

    public Teacher create(User user) {

        User newUser = userCrudService.create(user);
        newUser.setRoles(Set.of(Role.TEACHER));

        return repository.save(Teacher.builder()
                .user(newUser)
                .build());
    }

    public Teacher read(long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Teacher not found with id: " + id));
    }

    public User update(Long id, User user) {
        return userCrudService.update(id, user);
    }

    public void delete(long id) {
        Teacher teacher = repository.findById(id).orElseThrow(()-> new NoSuchElementException("Teacher not found with id: " + id));

        if (userUtilsService.existsStudentsAssignedToTeacher(teacher))
            throw new BadRequestException("Cannot delete teacher with assigned students");

        repository.delete(teacher);
    }

    public Page<Teacher> list(Pageable pageable, String q) {
        return repository.findBy(
                TeacherSpecification.searchByTerm(q),
                p -> p.page(pageable)
        );
    }


}
