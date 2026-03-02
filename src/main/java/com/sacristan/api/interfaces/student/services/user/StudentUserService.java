package com.sacristan.api.interfaces.student.services.user;

import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.StudentRepository;
import com.sacristan.api.global.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentUserService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public Student getStudentByUserId(User user) {
        return studentRepository.findById(user.getId())
                .orElseThrow(() -> new NoSuchElementException("Student not found for user ID: " + user.getId()));
    }

    public Teacher getTeacherByUserId(User user) {
        Student student = getStudentByUserId(user);
        return teacherRepository.findById(student.getTeacher().getId())
                .orElseThrow(() -> new NoSuchElementException("Teacher not found for user ID: " + student.getTeacher().getId()));
    }
}
