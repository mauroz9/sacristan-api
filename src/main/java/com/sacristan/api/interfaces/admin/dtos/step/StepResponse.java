package com.sacristan.api.interfaces.admin.dtos.step;

import com.sacristan.api.global.models.Step;

import java.time.Duration;

public record StepResponse(
        Long id,
        String name,
        Integer position,
        Duration estimatedDuration,
        Integer arasaacPictogramId
) {
    public static StepResponse of(Step step) {
        return new StepResponse(
                step.getId(),
                step.getName(),
                step.getPosition(),
                step.getEstimatedDuration(),
                step.getArasaacPictogramId()
        );
    }
}
