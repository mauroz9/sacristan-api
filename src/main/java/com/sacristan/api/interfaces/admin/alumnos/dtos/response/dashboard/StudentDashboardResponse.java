package com.sacristan.api.interfaces.admin.alumnos.dtos.response.dashboard;

import java.util.List;

public record StudentDashboardResponse(
        StudentStatsDTO stats,
        List<DailyProgressDTO> progresoSemanal,
        List<CategoryStatDTO> categoriasTrabajadas,
        List<AssignedSequenceProgressDTO> secuenciasAsignadas,
        List<RecentActivityDTO> actividadReciente
) {}