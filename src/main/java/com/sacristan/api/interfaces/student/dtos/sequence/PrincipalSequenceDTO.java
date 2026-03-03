package com.sacristan.api.interfaces.student.dtos.sequence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sacristan.api.global.models.Sequence;

import java.time.LocalTime;

public record PrincipalSequenceDTO (
        Long id,
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime startTime
) {
    public static PrincipalSequenceDTO from(Sequence s, LocalTime startTime) {
        return new PrincipalSequenceDTO(
                s.getId(),
                s.getTitle(),
                startTime != null ? LocalTime.now() : null
        );
    }
}
