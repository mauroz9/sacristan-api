package com.sacristan.api.interfaces.admin.extra.dtos;

import com.sacristan.api.global.entities.content.category.Category;

public record ReadCategoryResponse (
        Long id,
        String name
) {

    public static ReadCategoryResponse ofEntity(Category category) {
        return new ReadCategoryResponse(
                category.getId(),
                category.getName()
        );
    }

}
