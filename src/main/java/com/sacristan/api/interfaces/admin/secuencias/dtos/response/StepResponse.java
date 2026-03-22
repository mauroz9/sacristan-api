package com.sacristan.api.interfaces.admin.secuencias.dtos.response;

import com.sacristan.api.global.entities.content.step.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class StepResponse {

    private Long id;
    private String name;
    private Integer position;
    private Duration estimatedDuration;
    private Integer arasaacPictogramId;

    public static StepResponse ofEntity(Step step) {
        return StepResponse.builder()
                .id(step.getId())
                .name(step.getName())
                .position(step.getPosition())
                .estimatedDuration(step.getEstimatedDuration())
                .arasaacPictogramId(step.getArasaacPictogramId())
                .build();
    }
}

