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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ToString.Exclude
    @OneToMany(mappedBy = "sequence", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Step> steps = new ArrayList<>();

    public Sequence modify(Sequence newSequence){
        return Sequence.builder()
                .id(this.id)
                .title(newSequence.getTitle())
                .description(newSequence.getDescription())
                .estimatedDuration(newSequence.getEstimatedDuration())
                .allowGoBack(newSequence.getAllowGoBack())
                .category(newSequence.getCategory())
                .steps(newSequence.getSteps())
                .build();
    }

}
