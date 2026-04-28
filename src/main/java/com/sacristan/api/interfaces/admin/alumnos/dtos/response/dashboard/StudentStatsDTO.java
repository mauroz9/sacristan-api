package com.sacristan.api.interfaces.admin.alumnos.dtos.response.dashboard;

public record StudentStatsDTO(
        Integer secuenciasCompletadasHoy,
        Integer secuenciasPendientesHoy,
        Integer tasaExito,
        Double tiempoPromedio,
        Integer racha,
        String ultimaActividad
) {}