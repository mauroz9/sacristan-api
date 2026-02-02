package com.sacristan.api.global.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
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
    @Column(nullable = false)
    private Long id;

    private String name;
    private Integer position;

    @Nullable
    private Duration estimatedDuration;

    private Integer arasaacPictogramId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sequence_id")
    private Sequence sequence;

    public Step modify(Step newStep){
        this.name = newStep.getName();
        this.position = newStep.getPosition();
        this.estimatedDuration = newStep.getEstimatedDuration();
        this.arasaacPictogramId = newStep.getArasaacPictogramId();
        return this;
    }

}
