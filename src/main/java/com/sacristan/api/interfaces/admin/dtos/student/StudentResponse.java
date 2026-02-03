package com.sacristan.api.interfaces.admin.dtos.student;

import com.sacristan.api.interfaces.admin.dtos.teacher.TeacherResponse;
import com.sacristan.api.interfaces.admin.dtos.user.UserResponse;
import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.models.user.extra.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@SuperBuilder
public class StudentResponse extends UserResponse {

    private TeacherResponse teacher;
    private String role;

    public StudentResponse(UserResponse userResponse ) {
        id = userResponse.getId();
        name = userResponse.getName();
        lastName = userResponse.getLastName();
        username = userResponse.getUsername();
        email = userResponse.getEmail();
    }

    public static StudentResponse of(User user) {
        StudentResponse studentResponse = new StudentResponse(UserResponse.of(user));
        studentResponse.setRole(Role.STUDENT.name());
        return studentResponse;
    }

    public static StudentResponse of(Student student) {
        StudentResponse studentResponse = of(student.getUser());
        if (studentResponse.getTeacher() != null)
            studentResponse.setTeacher(TeacherResponse.of(student.getTeacher()));
        return studentResponse;
    }


}
