package com.sacristan.api.interfaces.admin.alumnos.dtos.response.dashboard;

public record AssignedSequenceProgressDTO(
        Long id,
        String nombre,
        String categoria,
        Integer completadas,
        Integer total,
        String ultimaVez
) {}