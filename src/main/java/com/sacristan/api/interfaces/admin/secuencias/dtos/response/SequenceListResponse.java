package com.sacristan.api.interfaces.admin.secuencias.dtos.response;

import com.sacristan.api.global.entities.content.sequence.Sequence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SequenceListResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private Integer stepCount;

    public static SequenceListResponse ofEntity(Sequence sequence) {
        return SequenceListResponse.builder()
                .id(sequence.getId())
                .title(sequence.getTitle())
                .description(sequence.getDescription())
                .category(sequence.getCategory() != null ? sequence.getCategory().getName() : null)
                .stepCount(sequence.getSteps() != null ? sequence.getSteps().size() : 0)
                .build();
    }
}

