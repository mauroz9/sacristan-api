package com.sacristan.api.interfaces.shared.dtos;

import com.sacristan.api.global.entities.users.teacher.Teacher;
import com.sacristan.api.global.entities.users.user.User;

public record ReadUserResponse(
        Long id,
        String name,
        String lastName,
        String email,
        String username
) {

    public static ReadUserResponse ofEntity(User user) {
        return new ReadUserResponse(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername()
        );
    }

}
