package com.sacristan.api.interfaces.user.controllers;

import com.sacristan.api.global.models.user.User;
import com.sacristan.api.interfaces.user.dtos.sequence.LibrarySequenceDTO;
import com.sacristan.api.interfaces.user.services.UserSequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller(value = "UserSequenceController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/sequence")
public class UserSequenceController {

    private final UserSequenceService service;

    @GetMapping
    public ResponseEntity<Page<LibrarySequenceDTO>> list(
            Pageable pageable,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(service.list(pageable, user).map(LibrarySequenceDTO::from));
    }



}
