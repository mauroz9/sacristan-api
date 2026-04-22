package com.sacristan.api.interfaces.admin.secuencias.dtos.request;

import com.sacristan.api.global.entities.content.step.Step;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.Duration;

public record UpdateStepRequest(
        @Length(min = 1, message = "{validation.empty.step.name}")
        @NotBlank(message = "{validation.empty.step.name}")
        String name,

        @NotNull(message = "{validation.empty.step.position}")
        @Min(value = 1, message = "{validation.invalid.step.position}")
        Integer position,

        Duration estimatedDuration,

        @NotNull(message = "{validation.empty.step.pictogram}")
        Integer arasaacPictogramId
) {

    public Step toEntity() {
        return Step.builder()
                .name(name)
                .position(position)
                .estimatedDuration(estimatedDuration)
                .arasaacPictogramId(arasaacPictogramId)
                .build();
    }
}


