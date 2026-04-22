package com.sacristan.api.interfaces.admin.rutinas.dtos.request;

import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.global.entities.content.category.Category;
import com.sacristan.api.global.entities.content.weekDays.DaysOfTheWeek;
import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegment;
import com.sacristan.api.global.validation.anotations.ValidCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

public record CreateRoutineRequest(
        @Length(min = 1, message = "{validation.empty.name}")
        @NotBlank(message = "{validation.empty.name}")
        String name,

        @NotNull(message = "{validation.empty.category}")
        @ValidCategory
        Long categoryId,

        @NotEmpty(message = "{validation.empty.daysOfTheWeek}")
        Set<DaysOfTheWeek> daysOfTheWeek,

        @Valid
        @NotNull(message = "{validation.empty.sequences}")
        List<CreateRoutineSegmentRequest> sequences
) {

    public Routine toEntity(List<RoutineSegment> segments) {
        return Routine.builder()
                .name(name)
                .category(Category.builder().id(categoryId).build())
                .daysOfTheWeek(daysOfTheWeek)
                .sequences(segments)
                .build();
    }
}


