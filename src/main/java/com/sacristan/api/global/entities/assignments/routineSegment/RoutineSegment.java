package com.sacristan.api.global.entities.assignments.routineSegment;

import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "routine_segments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoutineSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    @ToString.Exclude
    private Routine routine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sequence_id")
    @ToString.Exclude
    private Sequence sequence;

    private LocalTime startTime;
    private LocalTime endTime;

}
