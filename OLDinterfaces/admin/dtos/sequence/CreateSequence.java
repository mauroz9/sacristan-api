package com.sacristan.api.OLDinterfaces.admin.dtos.sequence;

import com.sacristan.api.interfaces.admin.dtos.step.CreateStep;
import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.Step;

import java.time.Duration;
import java.util.List;

public record CreateSequence(
        String title,
        String description,
        Duration estimatedDuration,
        Boolean allowGoBack,
        Long categoryId,
        List<CreateStep> steps,
        Integer frontPage
) {
    public Sequence to() {
        Sequence sequence = new Sequence();
        sequence.setTitle(this.title);
        sequence.setDescription(this.description);
        sequence.setEstimatedDuration(this.estimatedDuration);
        sequence.setAllowGoBack(this.allowGoBack);

        if (this.categoryId != null) {
            Category category = new Category();
            category.setId(this.categoryId);
            sequence.setCategory(category);
        }

        if (this.steps != null && !this.steps.isEmpty()) {
            List<Step> stepList = this.steps.stream()
                    .map(createStep -> {
                        Step step = createStep.to();
                        step.setSequence(sequence); // Establecer relación bidireccional
                        return step;
                    })
                    .toList();
            sequence.setSteps(stepList);
        }

        sequence.setFrontPage(this.frontPage);

        return sequence;
    }
}
