package com.sacristan.api.interfaces.admin.services.student;

import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentUtilsService {

    private final StudentRepository repository;

    public int getSequenceCount(
            Long id
    ) {
        return repository.countSequencesByStudentId(id);
    }
}
