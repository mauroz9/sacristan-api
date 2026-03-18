package com.sacristan.api.OLDinterfaces.admin.dashboard.dtos;

import java.time.LocalDateTime;

public record LatestReproductionsDto(String studentName, String sequenceTitle, LocalDateTime endedAt) {
}

