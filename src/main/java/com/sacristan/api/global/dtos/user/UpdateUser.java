package com.sacristan.api.global.dtos.user;

import com.sacristan.api.global.models.user.User;

public record UpdateUser (
        String name,
        String LastName,
        String email,
        String username
) {

    public User to(){
        return User.builder()
                .name(name)
                .lastName(LastName)
                .email(email)
                .username(username)
                .build();
    }

}
