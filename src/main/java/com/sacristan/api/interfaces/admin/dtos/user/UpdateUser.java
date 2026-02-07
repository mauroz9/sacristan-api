package com.sacristan.api.interfaces.admin.dtos.user;

import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.validation.anotations.UniqueUpdateEmail;
import jakarta.validation.constraints.NotBlank;

public record UpdateUser (
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @UniqueUpdateEmail
        String email,
        @UniqueUpdateUsername
        String username
) {

    public User to(){
        return User.builder()
                .name(name)
                .lastName(lastName)
                .email(email)
                .username(username)
                .build();
    }

}
