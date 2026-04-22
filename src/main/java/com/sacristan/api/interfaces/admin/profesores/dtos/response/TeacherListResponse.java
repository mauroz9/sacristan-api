package com.sacristan.api.interfaces.admin.profesores.dtos.response;

import com.sacristan.api.global.entities.users.teacher.Teacher;

public record TeacherListResponse(
        Long id,
        String name,
        String lastName,
        int studentCount
) {

    public static TeacherListResponse ofEntity(Teacher teacher, int studentCount) {
        return new TeacherListResponse(
                teacher.getId(),
                teacher.getUser().getName(),
                teacher.getUser().getLastName(),
                studentCount
        );
    }

}
