package com.sacristan.api.global.models.user;

import com.sacristan.api.global.models.Routine;
import com.sacristan.api.global.models.Sequence;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_user_id")
    @Builder.Default
    private List<Routine> routines = new ArrayList<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Sequence> sequences = new ArrayList<>();

    public void assignSequence(Sequence sequence){
        this.sequences.add(sequence);
    }

    public void unassignSequence(Sequence sequence){
        this.sequences.remove(sequence);
    }
}

