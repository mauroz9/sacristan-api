package com.sacristan.api.global.entities.users.student;

import com.sacristan.api.global.entities.users.teacher.Teacher;
import com.sacristan.api.global.services.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentModelService extends BaseServiceImpl<Student, Long, StudentRepository> {

    public int countByTeacher(Long teacherId) {
        return repository.getStudentCountByTeacherId(teacherId);
    }

    public List<Student> findByTeacher(Teacher teacher) {
        return repository.findByTeacher(teacher);
    }
}



