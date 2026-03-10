package com.sacristan.api.interfaces.student.dtos.sequence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sacristan.api.global.models.Sequence;

import java.time.LocalTime;

public record PrincipalSequenceDTO (
        Long id,
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime startTime,
        Integer frontImageId,
        Long routineSequenceId
) {
    public static PrincipalSequenceDTO from(Sequence s, LocalTime startTime, Long routineSequenceId) {
        return new PrincipalSequenceDTO(
                s.getId(),
                s.getTitle(),
                startTime,
                s.getFrontPage() != null ? s.getFrontPage() : s.getSteps().getFirst().getArasaacPictogramId(),
                routineSequenceId
        );
    }
}
