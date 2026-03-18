package com.sacristan.api.OLDinterfaces.admin.dtos.routine;

import com.sacristan.api.global.models.DaysOfTheWeek;
import com.sacristan.api.global.models.Routine;
import com.sacristan.api.interfaces.admin.dtos.category.CategoryResponse;

import java.util.List;
import java.util.Set;

public record RoutineResponse(
        Long id,
        String name,
        CategoryResponse category,
        Set<DaysOfTheWeek> daysOfTheWeek,
        List<RoutineSequenceResponse> sequences
) {
    public static RoutineResponse of(Routine routine) {
        return new RoutineResponse(
                routine.getId(),
                routine.getName(),
                routine.getCategory() != null ? CategoryResponse.of(routine.getCategory()) : null,
                routine.getDaysOfTheWeek(),
                routine.getSequences() != null ?
                        routine.getSequences().stream()
                                .map(RoutineSequenceResponse::of)
                                .toList()
                        : List.of()
        );
    }
}
