package com.sacristan.api.global.dtos.category;

import com.sacristan.api.global.models.Category;

public record CreateCategory (String name) {
    public Category to() {
        Category category = new Category();
        category.setName(this.name);
        return category;
    }
}
