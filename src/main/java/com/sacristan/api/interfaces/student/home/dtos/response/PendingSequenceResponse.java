package com.sacristan.api.interfaces.student.home.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import org.springframework.cglib.core.Local;

import java.time.LocalTime;

public record PendingSequenceResponse(
        Long id,
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime startTime,
        Integer frontImageId,
        Long routineSequenceId
) {

    public static PendingSequenceResponse from(Sequence s, LocalTime startTime, Long routineSequenceId) {
        return new PendingSequenceResponse(
                s.getId(),
                s.getTitle(),
                startTime,
                s.getFrontPage() != null ? s.getFrontPage() : s.getSteps().getFirst().getArasaacPictogramId(),
                routineSequenceId
        );
    }

}

