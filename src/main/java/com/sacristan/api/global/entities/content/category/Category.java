package com.sacristan.api.global.entities.content.category;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Category modify(Category category) {
        return Category.builder()
                .id(this.id)
                .name(category.name).build();
    }

}
