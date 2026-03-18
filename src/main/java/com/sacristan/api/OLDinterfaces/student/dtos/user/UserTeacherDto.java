package com.sacristan.api.OLDinterfaces.student.dtos.user;

import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.models.user.User;

public record UserTeacherDto(
        Long id,
        String username,
        String email,
        String name,
        String lastName
) {
    public static UserTeacherDto from(Teacher teacher) {
        if (teacher == null || teacher.getUser() == null) {
            return null;
        }
        User user = teacher.getUser();
        return new UserTeacherDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getLastName()
        );
    }
}
