package com.sacristan.api.interfaces.admin.dtos.reproduction;

import com.sacristan.api.global.models.Reproduction;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.Student;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public record CreateReproduction(
        Long sequenceId,
        Long studentId,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        Map<Long, Integer> stepReproductions,
        Map<Long, Long> reproductionTime,
        Map<String, Integer> buttonsClicks
) {
    public Reproduction to() {
        Reproduction reproduction = new Reproduction();

        if (this.sequenceId != null) {
            Sequence sequence = new Sequence();
            sequence.setId(this.sequenceId);
            reproduction.setSequence(sequence);
        }

        if (this.studentId != null) {
            Student student = new Student();
            student.setId(this.studentId);
            reproduction.setStudent(student);
        }

        reproduction.setStartedAt(this.startedAt);
        reproduction.setEndedAt(this.endedAt);
        reproduction.setStepReproductions(this.stepReproductions != null ? new HashMap<>(this.stepReproductions) : new HashMap<>());
        reproduction.setReproductionTime(this.reproductionTime != null ? new HashMap<>(this.reproductionTime) : new HashMap<>());
        reproduction.setButtonsClicks(this.buttonsClicks != null ? new HashMap<>(this.buttonsClicks) : new HashMap<>());

        return reproduction;
    }
}
