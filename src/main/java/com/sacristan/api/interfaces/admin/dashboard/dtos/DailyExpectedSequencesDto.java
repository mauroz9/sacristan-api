package com.sacristan.api.interfaces.admin.dashboard.dtos;

import java.time.LocalDate;

public record DailyExpectedSequencesDto(LocalDate date, long expected, long completed) {
}

