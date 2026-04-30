package com.sacristan.api.interfaces.student.perfil;

import com.sacristan.api.global.entities.users.student.Student;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import com.sacristan.api.global.entities.users.teacher.Teacher;
import com.sacristan.api.global.entities.users.teacher.TeacherModelService;
import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.interfaces.shared.services.StudentStatsService;
import com.sacristan.api.interfaces.student.perfil.dtos.response.StudentPunctuationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final StudentModelService studentModelService;
    private final TeacherModelService teacherModelService;
    private final StudentStatsService studentStatsService;

    public Student getProfile(User user) {
        return studentModelService.getById(user.getId());
    }

    public Teacher getTeacher(User user) {
        Student student = this.getProfile(user);
        return teacherModelService.getById(student.getTeacher().getId());
    }

    public StudentPunctuationResponse getPunctuation(User user) {
        int completed = studentStatsService.calculateCompletedReproductions(user.getId());
        int pending = studentStatsService.calculatePendingToday(user.getId());
        int streak = studentStatsService.calculateStreakForStudent(user.getId());

        return new StudentPunctuationResponse(completed, pending, streak);
    }

}
