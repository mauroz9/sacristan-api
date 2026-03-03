package com.sacristan.api.interfaces.student.dtos.step;

import com.sacristan.api.global.models.Step;

public record PlaySequenceStepDTO(
        Long id,
        String name,
        Integer position,
        Integer arasaacPictogramId
) {

    public static PlaySequenceStepDTO from(Step step) {
        return new PlaySequenceStepDTO(
                step.getId(),
                step.getName(),
                step.getPosition(),
                step.getArasaacPictogramId()
        );
    }
}
