package com.sacristan.api.global.security.utils.dtos;

import com.sacristan.api.interfaces.admin.dtos.user.UserResponse;
import com.sacristan.api.global.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
public class JwtUserResponse extends UserResponse {

    private String token;
    private String refreshToken;

    public JwtUserResponse(UserResponse userResponse) {
        this.id = userResponse.getId();
        this.name = userResponse.getName();
        this.lastName = userResponse.getLastName();
        this.username = userResponse.getUsername();
        this.email = userResponse.getEmail();
    }

    public static JwtUserResponse of(User user, String token, String refreshToken) {

        JwtUserResponse result = new JwtUserResponse(UserResponse.of(user));
        result.token = token;
        result.refreshToken = refreshToken;
        return result;

    }

}
