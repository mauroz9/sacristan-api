package com.sacristan.api.interfaces.student.dtos.sequence;

import com.sacristan.api.global.models.Sequence;

public record LibrarySequenceDTO(
        Long id,
        String title,
        String description,
        String category,
        Integer steps
) {

    public static LibrarySequenceDTO from(Sequence sequence) {
        return new LibrarySequenceDTO(
                sequence.getId(),
                sequence.getTitle(),
                sequence.getDescription(),
                sequence.getCategory() != null ? sequence.getCategory().getName() : null,
                sequence.getSteps() != null ? sequence.getSteps().size() : 0
        );
    }

}
