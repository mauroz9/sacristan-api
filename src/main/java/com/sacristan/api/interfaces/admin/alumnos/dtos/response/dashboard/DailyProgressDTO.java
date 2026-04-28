package com.sacristan.api.interfaces.admin.alumnos.dtos.response.dashboard;

public record DailyProgressDTO(
        String date,
        Integer completed
) {}