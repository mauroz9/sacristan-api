package com.sacristan.api.interfaces.student.perfil;

import com.sacristan.api.interfaces.student.perfil.dtos.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student/user")
public class PerfilController {

    private final PerfilService service;

    // * PROFILE
    @GetMapping("/profile")
    public ResponseEntity<@Nullable UserResponse> getProfile() {
        return ResponseEntity.ok(service.getProfile());
    }

    // * TEACHER
    @GetMapping("/teacher")
    public ResponseEntity<@Nullable UserResponse> getTeacher() {
        return ResponseEntity.ok(service.getTeacher());
    }
}
