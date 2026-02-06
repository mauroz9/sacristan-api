package com.sacristan.api.interfaces.admin.services.mixed;

import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.interfaces.admin.services.model.student.StudentUtilsService;
import com.sacristan.api.interfaces.admin.services.model.teacher.TeacherUtilsService;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentTeacherService {

    private final StudentUtilsService studentUtilsService;
    private final TeacherUtilsService teacherUtilsService;

    public List<Student> getStudentsWithTeacher(Long id) {
        Teacher t = id == null ? null : teacherUtilsService.getReferenceById(id);
        return studentUtilsService.findByTeacher(t);
    }

    public Student unassignTeacher(Long id) {
        Student s = studentUtilsService.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));

        if (s.getTeacher() == null)
            throw new IllegalArgumentException("Student with id: " + id + " has no assigned teacher");

        s.setTeacher(null);

        return studentUtilsService.save(s);

    }

    public Student assignTeacher(Long idStudent, Long idTeacher) {
        Student s = studentUtilsService.findById(idStudent).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + idStudent));
        Teacher t = teacherUtilsService.findById(idTeacher).orElseThrow(() -> new IllegalArgumentException("Teacher not found with id: " + idTeacher));

        if (s.getTeacher() != null)
            throw new IllegalArgumentException("Student with id: " + idStudent + " already has an assigned teacher");

        s.setTeacher(t);

        return studentUtilsService.save(s);

    }

    public Integer getStudentCountByTeacherId(Long id) {
        return studentUtilsService.getStudentCountByTeacherId(id);
    }
}
