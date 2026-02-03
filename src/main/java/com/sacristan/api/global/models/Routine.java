package com.sacristan.api.global.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "routines")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ElementCollection(targetClass = DaysOfTheWeek.class)
    @CollectionTable(name = "routine_days", joinColumns = @JoinColumn(name = "routine_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_the_week", nullable = false)
    private Set<DaysOfTheWeek> daysOfTheWeek = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RoutineSequence> sequences = new ArrayList<>();

    public Routine modify(Routine newRoutine) {
        return Routine.builder()
                .id(this.id)
                .name(newRoutine.getName())
                .category(newRoutine.getCategory())
                .daysOfTheWeek(newRoutine.getDaysOfTheWeek())
                .sequences(newRoutine.getSequences())
                .build();
    }
}
