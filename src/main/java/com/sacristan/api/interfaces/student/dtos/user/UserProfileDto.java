package com.sacristan.api.interfaces.student.dtos.user;

import com.sacristan.api.global.models.user.Student;

public record UserProfileDto(
        Long id,
        String username,
        String email,
        String name,
        String lastName,
        UserTeacherDto teacher
) {
        public static UserProfileDto from(Student student) {
            if (student == null) {
                return null;
            }
            return new UserProfileDto(
                    student.getUser().getId(),
                    student.getUser().getUsername(),
                    student.getUser().getEmail(),
                    student.getUser().getName(),
                    student.getUser().getLastName(),
                    UserTeacherDto.from(student.getTeacher())
            );
        }
}
