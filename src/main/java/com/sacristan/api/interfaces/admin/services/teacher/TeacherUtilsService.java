package com.sacristan.api.interfaces.admin.services.teacher;

import com.sacristan.api.global.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TeacherUtilsService {

    private final TeacherRepository repository;


    public int getStudentCount(Long id) {
        return repository.countStudentsByTeacherId(id);
    }
}
