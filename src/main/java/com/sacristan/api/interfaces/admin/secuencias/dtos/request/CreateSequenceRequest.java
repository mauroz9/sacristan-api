package com.sacristan.api.interfaces.admin.secuencias.dtos.request;

import com.sacristan.api.global.entities.content.category.Category;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.validation.anotations.ValidCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public record CreateSequenceRequest(
        @Length(min = 1, message = "{validation.empty.title}")
        @NotBlank(message = "{validation.empty.title}")
        String title,

        @Length(min = 1, message = "{validation.empty.description}")
        String description,

        @NotNull(message = "{validation.empty.category}")
        @ValidCategory
        Long categoryId,

        Duration estimatedDuration,
        Boolean allowGoBack,

        Integer frontPage,

        @Valid
        @NotNull(message = "{validation.empty.steps}")
        List<CreateStepRequest> steps
) {

    public Sequence toEntity(Category category) {
        return Sequence.builder()
                .title(title)
                .description(description)
                .category(category)
                .estimatedDuration(estimatedDuration)
                .allowGoBack(allowGoBack != null ? allowGoBack : false)
                .frontPage(frontPage)
                .steps(steps.stream()
                        .map(CreateStepRequest::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}


