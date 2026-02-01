package com.sacristan.api.global.dtos.user;

import com.sacristan.api.global.models.user.User;

public record UserResponse(
        Long id,
        String name,
        String lastName
) {

    public static UserResponse of(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getLastName()
        );
    }

}