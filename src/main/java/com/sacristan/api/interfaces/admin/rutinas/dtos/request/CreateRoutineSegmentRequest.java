package com.sacristan.api.interfaces.admin.rutinas.dtos.request;

import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegment;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CreateRoutineSegmentRequest(
        @NotNull(message = "{validation.empty.sequenceId}")
        Long sequenceId,

        @NotNull(message = "{validation.empty.startTime}")
        LocalTime startTime,

        @NotNull(message = "{validation.empty.endTime}")
        LocalTime endTime
) {

    public RoutineSegment toEntity() {
        return RoutineSegment.builder()
                .sequence(Sequence.builder().id(sequenceId).build())
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}

