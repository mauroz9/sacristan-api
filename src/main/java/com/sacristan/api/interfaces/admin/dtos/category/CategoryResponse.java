package com.sacristan.api.interfaces.admin.dtos.category;

import com.sacristan.api.global.models.Category;

public record CategoryResponse(Long id, String name) {

    public static CategoryResponse of(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}
