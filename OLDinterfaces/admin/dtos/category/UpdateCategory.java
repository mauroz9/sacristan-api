package com.sacristan.api.OLDinterfaces.admin.dtos.category;

import com.sacristan.api.global.models.Category;

public record UpdateCategory(String name) {
    public Category to() {
        Category category = new Category();
        category.setName(this.name);
        return category;
    }
}
