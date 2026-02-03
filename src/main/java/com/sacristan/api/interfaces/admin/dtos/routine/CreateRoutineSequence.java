package com.sacristan.api.interfaces.admin.dtos.routine;

import com.sacristan.api.global.models.RoutineSequence;
import com.sacristan.api.global.models.Sequence;

import java.time.LocalTime;

public record CreateRoutineSequence(
        Long sequenceId,
        LocalTime startTime,
        LocalTime endTime
) {
    public RoutineSequence to() {
        RoutineSequence routineSequence = new RoutineSequence();
        
        if (this.sequenceId != null) {
            Sequence sequence = new Sequence();
            sequence.setId(this.sequenceId);
            routineSequence.setSequence(sequence);
        }
        
        routineSequence.setStartTime(this.startTime);
        routineSequence.setEndTime(this.endTime);
        
        return routineSequence;
    }
}
