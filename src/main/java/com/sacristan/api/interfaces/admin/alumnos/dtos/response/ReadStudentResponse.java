package com.sacristan.api.interfaces.admin.alumnos.dtos.response;

import com.sacristan.api.global.entities.users.student.Student;

public record ReadStudentResponse(
        Long id,
        String name,
        String lastName,
        String email,
        String username
) {

    public static ReadStudentResponse ofEntity(Student student) {
        return new ReadStudentResponse(
                student.getId(),
                student.getUser().getName(),
                student.getUser().getLastName(),
                student.getUser().getEmail(),
                student.getUser().getUsername()
        );
    }

}
