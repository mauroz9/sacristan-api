package com.sacristan.api.interfaces.shared.dtos;

import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.validation.anotations.Password;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public record CreateUserRequest(
        @Length(min = 1, message = "{validation.empty.name}")
        String name,
        @Length(min = 1, message = "{validation.empty.lastName}")
        String lastName,
        @Length(min = 1, message = "{validation.empty.email}") @Email(message = "{validation.invalid.email}")
        String email,
        @Length(min = 1, message = "{validation.empty.username}")
        String username,
        @Length(min = 8, message = "{validation.empty.password}") @Password
        String password
) {

    public User toEntity() {
        return User.builder()
                .name(name)
                .lastName(lastName)
                .email(email)
                .username(username)
                .password(password)
                .build();
    }

    public CreateUserRequest ofEntity(User user) {
        return new CreateUserRequest(
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );
    }

}
