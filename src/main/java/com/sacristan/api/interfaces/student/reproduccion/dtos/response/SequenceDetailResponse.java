package com.sacristan.api.interfaces.student.reproduccion.dtos.response;

import java.util.List;

public record SequenceDetailResponse(
        Long id,
        String title,
        String description,
        String category,
        List<StepResponse> steps
) {

    public static SequenceDetailResponse ofEntity(Object sequence) {
        // TODO: Implement conversion from entity to DTO
        return null;
    }

}

