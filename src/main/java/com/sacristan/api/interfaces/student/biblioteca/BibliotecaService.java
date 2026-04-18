package com.sacristan.api.interfaces.student.biblioteca;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BibliotecaService {

    public Page<?> getCategories(Pageable pageable) {
        // TODO: Implement get categories logic
        return null;
    }

    public Page<?> getSequences(Pageable pageable, Long categoryId, String search) {
        // TODO: Implement get sequences logic with optional filters
        return null;
    }
}

