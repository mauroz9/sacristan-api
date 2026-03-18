package com.sacristan.api.OLDinterfaces.admin.dtos.reproduction;

import com.sacristan.api.global.models.Reproduction;
import com.sacristan.api.interfaces.admin.dtos.routine.RoutineSequenceResponse;
import com.sacristan.api.interfaces.admin.dtos.sequence.SequenceResponse;
import com.sacristan.api.interfaces.admin.dtos.student.StudentResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public record ReproductionResponse(
        Long id,
        RoutineSequenceResponse routineSequenceResponse,
        StudentResponse student,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        Map<Long, Integer> stepReproductions,
        Map<Long, Long> reproductionTime,
        Map<String, Integer> buttonsClicks
) {
    public static ReproductionResponse of(Reproduction reproduction) {
        return new ReproductionResponse(
                reproduction.getId(),
                reproduction.getRoutineSequence() != null ? RoutineSequenceResponse.of(reproduction.getRoutineSequence()) : null,
                reproduction.getStudent() != null ? StudentResponse.of(reproduction.getStudent()) : null,
                reproduction.getStartedAt(),
                reproduction.getEndedAt(),
                reproduction.getStepReproductions() != null ? new HashMap<>(reproduction.getStepReproductions()) : new HashMap<>(),
                reproduction.getReproductionTime() != null ? new HashMap<>(reproduction.getReproductionTime()) : new HashMap<>(),
                reproduction.getButtonsClicks() != null ? new HashMap<>(reproduction.getButtonsClicks()) : new HashMap<>()
        );
    }
}
