package com.sacristan.api.global.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "steps")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long position;
    private Integer idPictogramaArasaac;
}
