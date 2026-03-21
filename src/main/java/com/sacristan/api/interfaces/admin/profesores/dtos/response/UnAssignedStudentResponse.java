package com.sacristan.api.interfaces.admin.profesores.dtos.response;

import com.sacristan.api.global.entities.users.student.Student;

public record UnAssignedStudentResponse(
        Long id,
        String name,
        String lastName
) {

    public static UnAssignedStudentResponse ofEntity(Student student) {
        return new UnAssignedStudentResponse(
                student.getId(),
                student.getUser().getName(),
                student.getUser().getLastName()
        );
    }

}
