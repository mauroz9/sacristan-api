package com.sacristan.api.interfaces.student.home;

import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.interfaces.student.home.dtos.response.PendingSequenceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class HomeController {

    private final HomeService service;

    @GetMapping("/routines")
    public ResponseEntity<Page<PendingSequenceResponse>> getPendingSequences(Pageable pageable, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.getPendingSequences(pageable, user));
    }
}

