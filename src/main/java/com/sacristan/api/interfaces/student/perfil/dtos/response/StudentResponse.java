package com.sacristan.api.interfaces.student.perfil.dtos.response;

import com.sacristan.api.global.entities.users.student.Student;
import com.sacristan.api.global.entities.users.user.User;

public record StudentResponse(
        Long id,
        String username,
        String email,
        String name,
        String lastName
) {

    public static StudentResponse ofEntity(Student student) {
        User user = student.getUser();
        return new StudentResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getLastName()
        );
    }

}

