package com.sacristan.api.global.models.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@Table(name = "teachers")
public class Teacher {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    @MapsId // Establishes that the Entity's ID and the referenced Entity's ID must be the same
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    @Builder.Default
    private List<Student> students = new ArrayList<>();

    /* -- HELPERS -- */
    public void addStudent(Student student) {
        students.add(student);
        student.setTeacher(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setTeacher(null);
    }


}
