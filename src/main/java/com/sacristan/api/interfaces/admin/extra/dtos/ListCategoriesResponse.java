package com.sacristan.api.interfaces.admin.extra.dtos;

import com.sacristan.api.global.entities.content.category.Category;

public record ListCategoriesResponse (
        Long id,
        String name
) {

    public static ListCategoriesResponse ofEntity(Category category) {
        return new ListCategoriesResponse(
                category.getId(),
                category.getName()
        );
    }

}
