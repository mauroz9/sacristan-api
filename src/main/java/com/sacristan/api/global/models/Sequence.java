package com.sacristan.api.global.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sequences")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Nullable
    private Duration estimatedDuration;
    
    private Boolean allowGoBack;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "sequence_id")
    private List<Step> steps = new ArrayList<>();

}
