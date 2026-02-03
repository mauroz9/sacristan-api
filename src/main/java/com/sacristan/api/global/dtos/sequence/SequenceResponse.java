package com.sacristan.api.global.dtos.sequence;

import com.sacristan.api.global.dtos.category.CategoryResponse;
import com.sacristan.api.global.dtos.step.StepResponse;
import com.sacristan.api.global.models.Sequence;

import java.time.Duration;
import java.util.List;

public record SequenceResponse(
        Long id,
        String title,
        String description,
        Duration estimatedDuration,
        Boolean allowGoBack,
        CategoryResponse category,
        List<StepResponse> steps
) {
    public static SequenceResponse of(Sequence sequence) {
        return new SequenceResponse(
                sequence.getId(),
                sequence.getTitle(),
                sequence.getDescription(),
                sequence.getEstimatedDuration(),
                sequence.getAllowGoBack(),
                sequence.getCategory() != null ? CategoryResponse.of(sequence.getCategory()) : null,
                sequence.getSteps() != null ?
                        sequence.getSteps().stream()
                                .map(StepResponse::of)
                                .toList()
                        : List.of()
        );
    }
}
