package com.sacristan.api.interfaces.student.perfil.dtos.response;

public record StudentPunctuationResponse(
        Integer completadas,
        Integer activas,
        Integer dias_racha
) {
}
