package com.sacristan.api.interfaces.student.dtos.sequence;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.interfaces.student.dtos.step.PlaySequenceStepDTO;

import java.util.List;

public record PlaySequenceDTO(
            Long id,
            String title,
            String description,
            String category,
            List<PlaySequenceStepDTO> steps
) {

    public static PlaySequenceDTO from(Sequence sequence) {
        return new PlaySequenceDTO(
                sequence.getId(),
                sequence.getTitle(),
                sequence.getDescription(),
                sequence.getCategory() != null ? sequence.getCategory().getName() : null,
                !sequence.getSteps().isEmpty() ? sequence.getSteps().stream().map(PlaySequenceStepDTO::from).toList() : List.of()
        );
    }
}
