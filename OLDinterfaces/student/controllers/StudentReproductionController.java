package com.sacristan.api.OLDinterfaces.student.controllers;

import com.sacristan.api.global.models.user.User;
import com.sacristan.api.interfaces.student.services.reproduction.StudentReproductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student/reproductions")
@RequiredArgsConstructor
public class StudentReproductionController {

    private final StudentReproductionService studentReproductionService;

    @PostMapping("/{id}")
    public ResponseEntity<Long> reproduce(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
            ) {

        return ResponseEntity.status(HttpStatus.CREATED).body(studentReproductionService.reproduce(id, user));
    }

    @PutMapping("/{id}/end")
    // Revisar si meter un PESQL para que solo el alumno que la empezo, pueda finalizarla.
    public ResponseEntity<?> finish(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        studentReproductionService.finish(id, user);
        return ResponseEntity.ok().build();
    }


}
