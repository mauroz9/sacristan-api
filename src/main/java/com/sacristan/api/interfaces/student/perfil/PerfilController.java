package com.sacristan.api.interfaces.student.perfil;

import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.interfaces.student.perfil.dtos.response.StudentPunctuationResponse;
import com.sacristan.api.interfaces.student.perfil.dtos.response.StudentResponse;
import com.sacristan.api.interfaces.student.perfil.dtos.response.TeacherResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student/user")
public class PerfilController {

    private final PerfilService service;

    @GetMapping("/profile")
    public ResponseEntity<@Nullable StudentResponse> getProfile(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(StudentResponse.ofEntity(service.getProfile(user)));
    }

    @GetMapping("/teacher")
    public ResponseEntity<@Nullable TeacherResponse> getTeacher(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(TeacherResponse.ofEntity(service.getTeacher(user)));
    }

    @GetMapping("/punctuation")
    public ResponseEntity<StudentPunctuationResponse> getPunctuation(
            @AuthenticationPrincipal User user
    ){
        return ResponseEntity.ok(service.getPunctuation(user));
    }
}
