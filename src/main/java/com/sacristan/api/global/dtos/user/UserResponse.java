package com.sacristan.api.global.dtos.user;

import com.sacristan.api.global.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
public class UserResponse {
    protected Long id;
    protected String name;
    protected String lastName;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();
    }

}