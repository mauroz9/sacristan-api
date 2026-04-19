package com.sacristan.api.interfaces.student.reproduccion.dtos.response;

import com.sacristan.api.global.entities.content.step.Step;

public record StepResponse(
        Long id,
        String name,
        Integer position,
        Integer arasaacPictogramId
) {

    public static StepResponse ofEntity(Step step) {
        return new StepResponse(
                step.getId(),
                step.getName(),
                step.getPosition(),
                step.getArasaacPictogramId()
        );
    }

}

