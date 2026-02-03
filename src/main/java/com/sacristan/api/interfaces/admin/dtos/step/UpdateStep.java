package com.sacristan.api.interfaces.admin.dtos.step;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.Step;

import java.time.Duration;

public record UpdateStep(
        String name,
        Integer position,
        Duration estimatedDuration,
        Integer arasaacPictogramId,
        Long sequenceId
) {
    public Step to() {
        Step step = new Step();
        step.setName(this.name);
        step.setPosition(this.position);
        step.setEstimatedDuration(this.estimatedDuration);
        step.setArasaacPictogramId(this.arasaacPictogramId);

        if (this.sequenceId != null) {
            Sequence sequence = new Sequence();
            sequence.setId(this.sequenceId);
            step.setSequence(sequence);
        }

        return step;
    }
}
