package com.sacristan.api.interfaces.admin.alumnos.dtos.response.reproduction;

public record StepTrackingDTO(
        Integer stepIndex,
        String text,
        Integer viewCount,
        String timeActive
) {}