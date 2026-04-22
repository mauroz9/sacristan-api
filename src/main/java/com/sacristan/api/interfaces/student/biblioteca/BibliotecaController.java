package com.sacristan.api.interfaces.student.biblioteca;

import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.interfaces.student.biblioteca.dtos.response.CategoryResponse;
import com.sacristan.api.interfaces.student.biblioteca.dtos.response.SequenceListResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class BibliotecaController {

    private final BibliotecaService service;

    @GetMapping("/categories")
    public ResponseEntity<@Nullable Page<CategoryResponse>> getCategories(Pageable pageable) {
        return ResponseEntity.ok(service.getCategories(pageable).map(c ->
            CategoryResponse.ofEntity(c, service.getAssignedSequences(c))
        ));
    }

    @GetMapping("/sequences")
    public ResponseEntity<@Nullable Page<SequenceListResponse>> getSequences(
            Pageable pageable,
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(
                service.getSequences(pageable, categoryId, search, user).map(SequenceListResponse::ofEntity)
        );
    }
}
