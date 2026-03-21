package com.sacristan.api.interfaces.admin.alumnos.dtos.response;

import com.sacristan.api.global.entities.content.sequence.Sequence;

public record SequenceResponse(
        Long id,
        String title,
        String category
) {

    public static SequenceResponse ofEntity(Sequence sequence) {
        return new SequenceResponse(
                sequence.getId(),
                sequence.getTitle(),
                sequence.getCategory() != null ? sequence.getCategory().getName() : null
        );
    }

}

