package com.sacristan.api.global.models;

import com.sacristan.api.global.models.user.Student;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Sequence duplicate(){
        return Sequence.builder()
                .title(this.title + " (copy)")
                .description(this.description)
                .estimatedDuration(this.estimatedDuration)
                .allowGoBack(this.allowGoBack)
                .category(this.category)
                .steps(new ArrayList<>())
                .build();
    }

}
