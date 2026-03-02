package com.sacristan.api.interfaces.student.controllers;

import com.sacristan.api.global.models.user.User;
import com.sacristan.api.interfaces.student.dtos.category.LibraryCategoryDTO;
import com.sacristan.api.interfaces.student.services.catgory.StudentCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/categories")
public class StudentCategoryController {

    private final StudentCategoryService service;

    @GetMapping()
    public ResponseEntity<Page<LibraryCategoryDTO>> list(
            Pageable pageable,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(
                service.list(
                        pageable, user
                )
        );
    }

}