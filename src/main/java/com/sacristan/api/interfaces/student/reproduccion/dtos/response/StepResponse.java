package com.sacristan.api.interfaces.student.reproduccion.dtos.response;

public record StepResponse(
        Long id,
        String name,
        Integer position,
        Long arasaacPictogramId
) {

    public static StepResponse ofEntity(Object step) {
        // TODO: Implement conversion from entity to DTO
        return null;
    }

}

