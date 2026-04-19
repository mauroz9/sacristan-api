package com.sacristan.api.interfaces.student.perfil.dtos.response;

public record UserResponse(
        Long id,
        String username,
        String email,
        String name,
        String lastName
) {

    public static UserResponse ofEntity(Object user) {
        // TODO: Implement conversion from entity to DTO
        return null;
    }

}

