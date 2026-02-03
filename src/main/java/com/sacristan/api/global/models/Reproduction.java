package com.sacristan.api.global.models;

import com.sacristan.api.global.models.user.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "reproductions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reproduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sequence_id")
    private Sequence sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_user_id")
    private Student student;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @ElementCollection
    @CollectionTable(name = "reproduction_step_counts", joinColumns = @JoinColumn(name = "reproduction_id"))
    @MapKeyColumn(name = "step_id")
    @Column(name = "reproductions_count")
    private Map<Long, Integer> stepReproductions = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "reproduction_step_times", joinColumns = @JoinColumn(name = "reproduction_id"))
    @MapKeyColumn(name = "step_id")
    @Column(name = "active_time_ms")
    private Map<Long, Long> reproductionTime = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "reproduction_clicks", joinColumns = @JoinColumn(name = "reproduction_id"))
    @MapKeyColumn(name = "button_name")
    @Column(name = "times_clicked")
    private Map<String, Integer> buttonsClicks = new HashMap<>();

    public Reproduction modify(Reproduction newReproduction) {
        return Reproduction.builder()
                .id(this.id)
                .sequence(newReproduction.getSequence())
                .student(newReproduction.getStudent())
                .startedAt(newReproduction.getStartedAt())
                .endedAt(newReproduction.getEndedAt())
                .stepReproductions(newReproduction.getStepReproductions())
                .reproductionTime(newReproduction.getReproductionTime())
                .buttonsClicks(newReproduction.getButtonsClicks())
                .build();
    }
}
