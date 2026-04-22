package com.sacristan.api.interfaces.admin.rutinas.dtos.response;

import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.interfaces.admin.extra.dtos.ReadCategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RoutineDetailResponse {

    private Long id;
    private String name;
    private CategoryDetailResponse category;
    private Set<String> daysOfTheWeek;
    private List<RoutineSegmentResponse> sequences;

    public static RoutineDetailResponse ofEntity(Routine routine) {
        return RoutineDetailResponse.builder()
                .id(routine.getId())
                .name(routine.getName())
                .category(routine.getCategory() != null ? CategoryDetailResponse.ofEntity(routine.getCategory()) : null)
                .daysOfTheWeek(routine.getDaysOfTheWeek() != null ?
                        routine.getDaysOfTheWeek().stream()
                                .map(Enum::toString)
                                .collect(Collectors.toSet()) :
                        Set.of())
                .sequences(routine.getSequences() != null ?
                        routine.getSequences().stream()
                                .map(RoutineSegmentResponse::ofEntity)
                                .collect(Collectors.toList()) :
                        List.of())
                .build();
    }
}

