package com.sacristan.api.interfaces.admin.alumnos.dtos.response.dashboard;

public record RecentActivityDTO(
        Long id,
        String secuencia,
        String fecha,
        String duracion,
        Boolean completada
) {}