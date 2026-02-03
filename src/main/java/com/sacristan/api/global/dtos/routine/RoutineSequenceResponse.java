package com.sacristan.api.global.dtos.routine;

import com.sacristan.api.global.dtos.sequence.SequenceResponse;
import com.sacristan.api.global.models.RoutineSequence;

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
