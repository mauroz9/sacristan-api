package com.sacristan.api.interfaces.student.services.user;

import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentUserService {

    private final StudentRepository studentRepository;

    public Student getStudentByUserId(Long userId) {
        return studentRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Student not found for user ID: " + userId));
    }
}
