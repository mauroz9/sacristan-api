package com.sacristan.api.interfaces.student.dtos.step;

import com.sacristan.api.global.models.Step;

public record StepDto(
        Long id,
        String name,
        Integer position,
        Integer arasaacPictogramId
) {

    public static StepDto from(Step step) {
        return new StepDto(
                step.getId(),
                step.getName(),
                step.getPosition(),
                step.getArasaacPictogramId()
        );
    }
}
