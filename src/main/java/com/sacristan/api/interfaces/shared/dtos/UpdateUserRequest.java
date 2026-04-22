package com.sacristan.api.interfaces.shared.dtos;

import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.validation.anotations.UniqueUpdateEmail;
import com.sacristan.api.global.validation.anotations.UniqueUpdateUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @UniqueUpdateEmail @Email(message = "{validation.invalid.email}") @NotBlank
        String email,
        @UniqueUpdateUsername @NotBlank
        String username
) {

    public User toEntity(){
        return User.builder()
                .name(name)
                .lastName(lastName)
                .email(email)
                .username(username)
                .build();
    }

}
