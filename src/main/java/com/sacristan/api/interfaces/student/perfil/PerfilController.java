package com.sacristan.api.interfaces.student.perfil;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student/user")
public class PerfilController {

    private final PerfilService service;

    @GetMapping("/profile")
    public ResponseEntity<@Nullable ?> getProfile() {
        return ResponseEntity.ok(service.getProfile());
    }

    @GetMapping("/teacher")
    public ResponseEntity<@Nullable ?> getTeacher() {
        return ResponseEntity.ok(service.getTeacher());
    }
}

