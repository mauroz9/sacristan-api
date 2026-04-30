package com.sacristan.api.interfaces.admin.alumnos.dtos.response.reproduction;

public record ButtonClicksDTO(
        Integer previous,
        Integer next,
        Integer complete,
        Integer exit
) {}
