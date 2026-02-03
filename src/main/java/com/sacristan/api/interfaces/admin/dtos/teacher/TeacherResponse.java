package com.sacristan.api.interfaces.admin.dtos.teacher;

import com.sacristan.api.interfaces.admin.dtos.user.UserResponse;
import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.models.user.extra.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@SuperBuilder
public class TeacherResponse extends UserResponse {

    private String role;

    public TeacherResponse ( UserResponse userResponse ) {
        id = userResponse.getId();
        name = userResponse.getName();
        lastName = userResponse.getLastName();
        username = userResponse.getUsername();
        email = userResponse.getEmail();
    }

    public static TeacherResponse of( User user) {
        TeacherResponse teacherResponse = new TeacherResponse(UserResponse.of(user));
        teacherResponse.setRole(Role.TEACHER.name());
        return teacherResponse;
    }

    public static TeacherResponse of( Teacher teacher) {
        return of(teacher.getUser());
    }


}
