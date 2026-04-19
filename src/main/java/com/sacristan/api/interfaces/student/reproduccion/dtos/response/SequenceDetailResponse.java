package com.sacristan.api.interfaces.student.reproduccion.dtos.response;

import com.sacristan.api.global.entities.content.sequence.Sequence;

import java.util.List;

public record SequenceDetailResponse(
        Long id,
        String title,
        String description,
        String category,
        List<StepResponse> steps
) {

    public static SequenceDetailResponse ofEntity(Sequence sequence) {
        return new SequenceDetailResponse(
                sequence.getId(),
                sequence.getTitle(),
                sequence.getDescription(),
                sequence.getCategory() != null ? sequence.getCategory().getName() : null,
                !sequence.getSteps().isEmpty() ? sequence.getSteps().stream().map(StepResponse::ofEntity).toList() : List.of()
        );
    }

}

