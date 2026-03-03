package com.sacristan.api.interfaces.student.dtos.sequence;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.interfaces.student.dtos.step.StepDto;

import java.util.List;

public record SequenceDto(
            Long id,
            String title,
            String description,
            String category,
            List<StepDto> steps
) {

    public static SequenceDto from(Sequence sequence) {
        return new SequenceDto(
                sequence.getId(),
                sequence.getTitle(),
                sequence.getDescription(),
                sequence.getCategory() != null ? sequence.getCategory().getName() : null,
                !sequence.getSteps().isEmpty() ? sequence.getSteps().stream().map(StepDto::from).toList() : List.of()
        );
    }
}
