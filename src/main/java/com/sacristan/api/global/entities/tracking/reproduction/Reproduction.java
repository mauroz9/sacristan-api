package com.sacristan.api.global.entities.tracking.reproduction;

import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegment;
import com.sacristan.api.global.entities.users.student.Student;
import com.sacristan.api.global.entities.tracking.status.Status;
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

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_segment_id")
    private RoutineSegment routineSegment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_user_id")
    private Student student;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @ElementCollection
    @CollectionTable(name = "reproduction_step_counts", joinColumns = @JoinColumn(name = "reproduction_id"))
    @MapKeyColumn(name = "step_id")
    @Column(name = "reproductions_count")
    @Builder.Default
    private Map<Integer, Integer> stepReproductions = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "reproduction_step_times", joinColumns = @JoinColumn(name = "reproduction_id"))
    @MapKeyColumn(name = "step_id")
    @Column(name = "active_time_ms")
    @Builder.Default
    private Map<Integer, Long> reproductionTime = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "reproduction_clicks", joinColumns = @JoinColumn(name = "reproduction_id"))
    @MapKeyColumn(name = "button_name")
    @Column(name = "times_clicked")
    @Builder.Default
    private Map<String, Integer> buttonsClicks = new HashMap<>();

    public Reproduction modify(Reproduction newReproduction) {
        return Reproduction.builder()
                .id(this.id)
                .routineSegment(newReproduction.getRoutineSegment())
                .student(newReproduction.getStudent())
                .startedAt(newReproduction.getStartedAt())
                .endedAt(newReproduction.getEndedAt())
                .stepReproductions(newReproduction.getStepReproductions())
                .reproductionTime(newReproduction.getReproductionTime())
                .buttonsClicks(newReproduction.getButtonsClicks())
                .build();
    }
}
