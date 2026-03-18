package com.sacristan.api.global.security.utils.dtos;

import com.sacristan.api.global.entities.users.role.Role;
import com.sacristan.api.global.entities.users.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
public class JwtUserResponse {

    private String email;
    private String token;
    private String refreshToken;
    private Set<String> roles;

    public static JwtUserResponse of(User user, String token, String refreshToken) {

        JwtUserResponse result = new JwtUserResponse();
        result.email = user.getEmail();
        result.token = token;
        result.refreshToken = refreshToken;
        result.roles = user.getRoles()
                .stream()
                .map(Role::name)
                .collect(java.util.stream.Collectors.toSet());
        return result;

    }

}
