package com.sacristan.api.interfaces.student.biblioteca.dtos.response;

import com.sacristan.api.global.entities.content.sequence.Sequence;

public record SequenceListResponse(
        Long id,
        String title,
        String description,
        String category,
        Integer steps
) {

    public static SequenceListResponse ofEntity(Sequence sequence) {
        return new SequenceListResponse(
                sequence.getId(),
                sequence.getTitle(),
                sequence.getDescription(),
                sequence.getCategory().getName(),
                sequence.getSteps().size()
        );
    }

}

