package com.sacristan.api.OLDinterfaces.admin.dtos.routine;

import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.DaysOfTheWeek;
import com.sacristan.api.global.models.Routine;
import com.sacristan.api.global.models.RoutineSequence;

import java.util.List;
import java.util.Set;

public record UpdateRoutine(
        String name,
        Long categoryId,
        Set<DaysOfTheWeek> daysOfTheWeek,
        List<UpdateRoutineSequence> sequences
) {
    public Routine to() {
        Routine routine = new Routine();
        routine.setName(this.name);
        
        if (this.categoryId != null) {
            Category category = new Category();
            category.setId(this.categoryId);
            routine.setCategory(category);
        }

        if (this.daysOfTheWeek != null) {
            routine.setDaysOfTheWeek(this.daysOfTheWeek);
        }

        if (this.sequences != null && !this.sequences.isEmpty()) {
            List<RoutineSequence> routineSequences = this.sequences.stream()
                    .map(ors -> {
                        RoutineSequence rs = ors.to();
                        rs.setRoutine(routine);
                        rs.setId(ors.id());
                        return rs;
                    })
                    .toList();
            routine.setSequences(routineSequences);
        }

        return routine;
    }
}
