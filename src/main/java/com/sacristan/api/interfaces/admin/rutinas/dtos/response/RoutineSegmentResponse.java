package com.sacristan.api.interfaces.admin.rutinas.dtos.response;

import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RoutineSegmentResponse {

    private Long id;
    private Long sequenceId;
    private String sequenceTitle;
    private LocalTime startTime;
    private LocalTime endTime;

    public static RoutineSegmentResponse ofEntity(RoutineSegment routineSegment) {
        return RoutineSegmentResponse.builder()
                .id(routineSegment.getId())
                .sequenceId(routineSegment.getSequence() != null ? routineSegment.getSequence().getId() : null)
                .sequenceTitle(routineSegment.getSequence() != null ? routineSegment.getSequence().getTitle() : null)
                .startTime(routineSegment.getStartTime())
                .endTime(routineSegment.getEndTime())
                .build();
    }
}

