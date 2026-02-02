package com.sacristan.api.global.models;

import com.sacristan.api.global.models.user.User;
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
                .name(this.name).build();
    }

}
