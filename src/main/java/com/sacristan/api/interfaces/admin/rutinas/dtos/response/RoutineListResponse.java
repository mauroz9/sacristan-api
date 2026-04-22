package com.sacristan.api.interfaces.admin.rutinas.dtos.response;

import com.sacristan.api.global.entities.content.rotuine.Routine;
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
public class RoutineListResponse {

    private Long id;
    private String name;
    private CategoryDetailResponse category;
    private Set<String> daysOfTheWeek;
    private Integer sequenceCount;

    public static RoutineListResponse ofEntity(Routine routine) {
        return RoutineListResponse.builder()
                .id(routine.getId())
                .name(routine.getName())
                .category(routine.getCategory() != null ? CategoryDetailResponse.ofEntity(routine.getCategory()) : null)
                .daysOfTheWeek(routine.getDaysOfTheWeek() != null ?
                        routine.getDaysOfTheWeek().stream()
                                .map(Enum::toString)
                                .collect(Collectors.toSet()) :
                        Set.of())
                .sequenceCount(routine.getSequences() != null ? routine.getSequences().size() : 0)
                .build();
    }
}

