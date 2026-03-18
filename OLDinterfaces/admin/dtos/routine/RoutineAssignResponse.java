package com.sacristan.api.OLDinterfaces.admin.dtos.routine;

import com.sacristan.api.global.models.DaysOfTheWeek;
import com.sacristan.api.global.models.Routine;
import com.sacristan.api.interfaces.admin.dtos.category.CategoryResponse;

import java.util.Set;
import java.util.stream.Collectors;

public record RoutineAssignResponse(
        Long id,
        String name,
        Set<String> daysOfWeek,
        CategoryResponse category
) {
    public static RoutineAssignResponse of(Routine routine) {
        return new RoutineAssignResponse(
                routine.getId(),
                routine.getName(),
                convertToLow(routine.getDaysOfTheWeek()),
                routine.getCategory() != null ? CategoryResponse.of(routine.getCategory()) : null
        );
    }

    private static Set<String> convertToLow(Set<DaysOfTheWeek> daysOfTheWeek) {
        return daysOfTheWeek.stream()
                .map(
                day -> day.name().substring(0, 1).toLowerCase()
                )
                .collect(Collectors.toSet());
    }
}
