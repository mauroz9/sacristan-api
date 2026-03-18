package com.sacristan.api.OLDinterfaces.student.controllers;

import com.sacristan.api.global.models.user.User;
import com.sacristan.api.interfaces.student.dtos.sequence.LibrarySequenceDTO;
import com.sacristan.api.interfaces.student.dtos.sequence.PlaySequenceDTO;
import com.sacristan.api.interfaces.student.services.sequence.StudentSequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller(value = "UserSequenceController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/student/sequences")
public class StudentSequenceController {

    private final StudentSequenceService service;

    @GetMapping
    public ResponseEntity<Page<LibrarySequenceDTO>> list(
            Pageable pageable,
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, name = "category") Long categoryId,
            @RequestParam(required = false, name = "search") String searchQuery

    ) {
        return ResponseEntity.ok(service.list(pageable, user, categoryId, searchQuery).map(LibrarySequenceDTO::from));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaySequenceDTO> getById(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(PlaySequenceDTO.from(service.getById(id)));
    }



}
