package com.sacristan.api.interfaces.admin.rutinas.dtos.response;

import com.sacristan.api.global.entities.content.category.Category;

public record CategoryDetailResponse (
        Long id,
        String name
)
{

        public static CategoryDetailResponse ofEntity(Category category) {
            return new CategoryDetailResponse(
                    category.getId(),
                    category.getName()
            );
        }

}
