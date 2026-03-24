package com.sacristan.api.interfaces.admin.profesores.dtos.response;

import com.sacristan.api.global.entities.users.teacher.Teacher;

public record ReadTeacherResponse(
        Long id,
        String name,
        String lastName,
        String email,
        String username
) {

    public static ReadTeacherResponse ofEntity(Teacher teacher) {
        return new ReadTeacherResponse(
                teacher.getId(),
                teacher.getUser().getName(),
                teacher.getUser().getLastName(),
                teacher.getUser().getEmail(),
                teacher.getUser().getUsername()
        );
    }

}
