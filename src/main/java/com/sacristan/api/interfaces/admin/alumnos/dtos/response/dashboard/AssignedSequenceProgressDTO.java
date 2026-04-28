package com.sacristan.api.interfaces.admin.alumnos.dtos.response.dashboard;

public record AssignedSequenceProgressDTO(
        Long id,
        String nombre,
        String categoria,
        String franjaHoraria,
        String estado
) {}