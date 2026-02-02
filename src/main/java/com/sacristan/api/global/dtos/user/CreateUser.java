package com.sacristan.api.global.dtos.user;

import com.sacristan.api.global.models.user.User;

public record CreateUser(
        String name,
        String lastName,
        String email,
        String username,
        String password,
        String verifyPassword
) {

    public User to() {
        return User.builder()
                .name(name)
                .lastName(lastName)
                .email(email)
                .username(username)
                .password(password)
                .build();
    }

}
