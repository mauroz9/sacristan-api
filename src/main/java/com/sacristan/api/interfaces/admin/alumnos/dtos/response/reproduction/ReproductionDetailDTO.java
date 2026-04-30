package com.sacristan.api.interfaces.admin.alumnos.dtos.response.reproduction;

import java.util.List;

public record ReproductionDetailDTO(
        Long id,
        String sequenceTitle,
        String studentName,
        String categoryName,
        String status,
        String startedAt,
        String endedAt,
        String duration,
        ButtonClicksDTO buttonClicks,
        List<StepTrackingDTO> steps
) {}
