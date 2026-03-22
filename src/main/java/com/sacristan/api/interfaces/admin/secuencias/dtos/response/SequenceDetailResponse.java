package com.sacristan.api.interfaces.admin.secuencias.dtos.response;

import com.sacristan.api.global.entities.content.sequence.Sequence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SequenceDetailResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private Duration estimatedDuration;
    private Boolean allowGoBack;
    private Integer frontPage;
    private List<StepResponse> steps;

    public static SequenceDetailResponse ofEntity(Sequence sequence) {
        return SequenceDetailResponse.builder()
                .id(sequence.getId())
                .title(sequence.getTitle())
                .description(sequence.getDescription())
                .category(sequence.getCategory() != null ? sequence.getCategory().getName() : null)
                .estimatedDuration(sequence.getEstimatedDuration())
                .allowGoBack(sequence.getAllowGoBack())
                .frontPage(sequence.getFrontPage())
                .steps(sequence.getSteps() != null ? 
                        sequence.getSteps().stream()
                                .map(StepResponse::ofEntity)
                                .collect(Collectors.toList()) : 
                        List.of())
                .build();
    }
}

