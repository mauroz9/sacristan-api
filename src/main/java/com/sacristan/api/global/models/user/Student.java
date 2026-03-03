package com.sacristan.api.global.models.user;

import com.sacristan.api.global.models.Routine;
import com.sacristan.api.global.models.Sequence;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@Table(name = "students")
public class Student {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    @MapsId // Establishes that the Entity's ID and the referenced Entity's ID must be the same
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private List<Routine> routines = new ArrayList<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Sequence> sequences = new HashSet<>();

    public void assignSequence(Sequence sequence){
        this.sequences.add(sequence);
    }

    public void unassignSequence(Sequence sequence){
        this.sequences.remove(sequence);
    }
}

