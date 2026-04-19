package com.sacristan.api.interfaces.student.home.dtos.response;

public record RoutineListResponse(
        Long id,
        String name,
        String startTime,
        Long frontImageId,
        Long routineSequenceId
) {

    public static RoutineListResponse ofEntity(Object routine) {
        // TODO: Implement conversion from entity to DTO
        return null;
    }

}

