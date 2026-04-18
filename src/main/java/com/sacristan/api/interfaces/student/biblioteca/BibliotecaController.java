package com.sacristan.api.interfaces.student.biblioteca;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class BibliotecaController {

    private final BibliotecaService service;

    @GetMapping("/categories")
    public ResponseEntity<@Nullable Page<?>> getCategories(Pageable pageable) {
        return ResponseEntity.ok(service.getCategories(pageable));
    }

    @GetMapping("/sequences")
    public ResponseEntity<@Nullable Page<?>> getSequences(
            Pageable pageable,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(service.getSequences(pageable, categoryId, search));
    }
}

