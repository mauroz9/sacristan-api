package com.sacristan.api.interfaces.student.dtos.category;

import com.sacristan.api.global.models.Category;

public record LibraryCategoryDTO(
        Long id,
        String name,
        Integer sequenceCount
) {
    public static LibraryCategoryDTO from(Category c, Integer sequenceCount) {
        return new LibraryCategoryDTO(
                c.getId(),
                c.getName(),
                sequenceCount
        );
    }
}
