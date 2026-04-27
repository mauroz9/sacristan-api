package com.sacristan.api.interfaces.admin.secuencias.dtos.response;

import com.sacristan.api.global.entities.content.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryResponse {

    private Long id;
    private String name;

    public static CategoryResponse ofEntity(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
