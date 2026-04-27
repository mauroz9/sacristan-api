package com.sacristan.api.interfaces.admin.profesores;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.users.role.Role;
import com.sacristan.api.global.entities.users.student.Student;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import com.sacristan.api.global.entities.users.teacher.Teacher;
import com.sacristan.api.global.entities.users.teacher.TeacherModelService;
import com.sacristan.api.global.entities.users.teacher.TeacherSpecification;
import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.entities.users.user.UserModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesoresService {

    private final TeacherModelService teacherModelService;
    private final UserModelService userModelService;
    private final StudentModelService studentModelService;

    public void create(Teacher teacher) {
        User createdUser = userModelService.create(teacher.getUser(), Role.TEACHER);
        teacher.setUser(createdUser);
        teacherModelService.save(teacher);
    }

    public void update(Long id, User user) {
        userModelService.update(id, user);
    }

    public void delete(Long id) {
        List<Student> studentList = getStudentsByTeacher(id);
        studentList.forEach(student -> unassignTeacher(student.getId()));
        teacherModelService.deleteById(id);
        userModelService.deleteById(id);
    }

    public Teacher getById(Long id) {
        return teacherModelService.getById(id);
    }

    public Page<Teacher> list(Pageable pageable, String q) {
        return teacherModelService.findByTerm(pageable, TeacherSpecification.searchByTerm(q));
    }

    public Integer getStudentCountByTeacherId(Long id) {
        return studentModelService.countByTeacher(id);
    }

    public List<SortParamDTO> getSortParams() {
        return TeacherModelService.getSortParams();
    }

    public List<Student> getStudentsByTeacher(Long teacherId) {
        if (teacherId == null) {
            return studentModelService.findByTeacher(null);
        }
        Teacher teacher = teacherModelService.getById(teacherId);
        return studentModelService.findByTeacher(teacher);
    }

    public Student unassignTeacher(Long studentId) {
        Student student = studentModelService.getById(studentId);

        if (student.getTeacher() == null) {
            throw new IllegalArgumentException("Student with id: " + studentId + " has no assigned teacher");
        }

        student.setTeacher(null);
        return studentModelService.save(student);
    }

    public Student assignTeacher(Long studentId, Long teacherId) {
        Student student = studentModelService.getById(studentId);
        Teacher teacher = teacherModelService.getById(teacherId);

        if (student.getTeacher() != null) {
            throw new IllegalArgumentException("Student with id: " + studentId + " already has an assigned teacher");
        }

        student.setTeacher(teacher);
        return studentModelService.save(student);
    }
}
