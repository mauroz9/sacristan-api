package com.sacristan.api.interfaces.admin.alumnos.dtos.response.dashboard;

public record StudentStatsDTO(
        Integer secuenciasCompletadas,
        Integer secuenciasEnProgreso,
        Integer tasaExito,
        Double tiempoPromedio,
        Integer racha,
        String ultimaActividad
) {}