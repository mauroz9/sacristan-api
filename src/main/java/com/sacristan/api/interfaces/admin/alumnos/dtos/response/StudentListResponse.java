package com.sacristan.api.interfaces.admin.alumnos.dtos.response;

import com.sacristan.api.global.entities.users.student.Student;

public record StudentListResponse(
        Long id,
        String name,
        String lastName,
        int sequencesCount,
        int routinesCount
) {

    public static StudentListResponse ofEntity(Student student, int sequencesCount, int routinesCount) {
        return new StudentListResponse(
                student.getId(),
                student.getUser().getName(),
                student.getUser().getLastName(),
                sequencesCount,
                routinesCount
        );
    }

}


