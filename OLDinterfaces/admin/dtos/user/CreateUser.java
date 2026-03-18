package com.sacristan.api.OLDinterfaces.admin.dtos.user;

import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.validation.anotations.PasswordsMatch;
import com.sacristan.api.global.validation.anotations.UniqueUpdateEmail;
import com.sacristan.api.global.validation.anotations.UniqueUpdateUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@PasswordsMatch.List({
        @PasswordsMatch(passwordField = "password", verifyPasswordField = "verifyPassword")
})
public record CreateUser(
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @UniqueUpdateEmail @Email
        String email,
        @UniqueUpdateUsername @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
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
