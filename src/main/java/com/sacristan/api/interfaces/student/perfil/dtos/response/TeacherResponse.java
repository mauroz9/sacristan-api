package com.sacristan.api.interfaces.student.perfil.dtos.response;

import com.sacristan.api.global.entities.users.teacher.Teacher;
import com.sacristan.api.global.entities.users.user.User;

public record TeacherResponse(
        Long id,
        String username,
        String email,
        String name,
        String lastName
) {

    public static TeacherResponse ofEntity(Teacher teacher) {
        User user = teacher.getUser();
        return new TeacherResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getLastName()
        );
    }

}

