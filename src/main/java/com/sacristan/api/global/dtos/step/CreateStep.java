package com.sacristan.api.global.dtos.step;

import com.sacristan.api.global.models.Step;

import java.time.Duration;

public record CreateStep(
        String name,
        Integer position,
        Duration estimatedDuration,
        Integer arasaacPictogramId
) {
    public Step to() {
        Step step = new Step();
        step.setName(this.name);
        step.setPosition(this.position);
        step.setEstimatedDuration(this.estimatedDuration);
        step.setArasaacPictogramId(this.arasaacPictogramId);
        return step;
    }
}
