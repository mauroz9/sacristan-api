package com.sacristan.api.interfaces.student.controllers;

import com.sacristan.api.global.models.user.User;
import com.sacristan.api.interfaces.student.dtos.user.UserProfileDto;
import com.sacristan.api.interfaces.student.services.user.StudentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "StudentUserController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/student/user")
public class StudentUserController {

    private final StudentUserService service;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile(
            @AuthenticationPrincipal User user
            ) {
        return ResponseEntity.ok(UserProfileDto.from(service.getStudentByUserId(user.getId())));
    }
}
