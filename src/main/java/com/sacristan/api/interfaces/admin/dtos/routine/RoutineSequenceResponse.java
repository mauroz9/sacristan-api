package com.sacristan.api.interfaces.admin.dtos.routine;


import com.sacristan.api.global.models.RoutineSequence;
import com.sacristan.api.interfaces.admin.dtos.sequence.SequenceResponse;

import java.time.LocalTime;

public record RoutineSequenceResponse(
        Long id,
        SequenceResponse sequence,
        LocalTime startTime,
        LocalTime endTime
) {
    public static RoutineSequenceResponse of(RoutineSequence routineSequence) {
        return new RoutineSequenceResponse(
                routineSequence.getId(),
                routineSequence.getSequence() != null ? SequenceResponse.of(routineSequence.getSequence()) : null,
                routineSequence.getStartTime(),
                routineSequence.getEndTime()
        );
    }
}
