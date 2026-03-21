package com.sacristan.api.interfaces.admin.profesores.dtos.response;

import com.sacristan.api.global.entities.users.student.Student;

public record AssignedStudentResponse(
        Long id,
        String name,
        String lastName,
        String email
) {

    public static AssignedStudentResponse ofEntity(Student student) {
        return new AssignedStudentResponse(
                student.getId(),
                student.getUser().getName(),
                student.getUser().getLastName(),
                student.getUser().getEmail()
        );
    }

}
