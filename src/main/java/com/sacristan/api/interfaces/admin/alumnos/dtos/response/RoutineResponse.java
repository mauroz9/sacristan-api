package com.sacristan.api.interfaces.admin.alumnos.dtos.response;

import com.sacristan.api.global.entities.content.rotuine.Routine;

public record RoutineResponse(
        Long id,
        String name,
        String category
) {

    public static RoutineResponse ofEntity(Routine routine) {
        return new RoutineResponse(
                routine.getId(),
                routine.getName(),
                routine.getCategory() != null ? routine.getCategory().getName() : null
        );
    }

}

