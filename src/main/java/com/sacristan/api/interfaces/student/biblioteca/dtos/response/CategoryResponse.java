package com.sacristan.api.interfaces.student.biblioteca.dtos.response;

import com.sacristan.api.global.entities.content.category.Category;

public record CategoryResponse(
        Long id,
        String name,
        Integer sequenceCount
) {

    public static CategoryResponse ofEntity(Category category, Integer sequenceCount) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                sequenceCount
        );
    }

}

