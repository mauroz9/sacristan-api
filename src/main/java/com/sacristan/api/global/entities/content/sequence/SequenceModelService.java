package com.sacristan.api.global.entities.content.sequence;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.content.category.Category;
import com.sacristan.api.global.services.BaseServiceImpl;
import com.sacristan.api.interfaces.admin.dashboard.dtos.MostUsedSequencesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.SequenceEndEvent;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SequenceModelService extends BaseServiceImpl<Sequence, Long, SequenceRepository> {

    public long count() {
        return repository.count();
    }

    public long countExpectedSequencesForDay(String dayName) {
        return repository.countExpectedSequencesForDay(dayName);
    }

    public long countCompletedSequencesForDay(LocalDateTime dayStart, LocalDateTime dayEnd, String completedStatus) {
        return repository.countCompletedSequencesForDay(dayStart, dayEnd, completedStatus);
    }

    public Page<MostUsedSequencesDto> findMostUsedSequences(Pageable pageable) {
        return repository.findMostUsedSequences(pageable);
    }

    public static List<SortParamDTO> getSortParams() {
        return List.of(
                new SortParamDTO("Título", "title"),
                new SortParamDTO("Descripción", "description"),
                new SortParamDTO("Categoría", "category.name")
        );
    }

    public Integer getSequencesByCategory(Category c) {
        return repository.countByCategory(c);
    }

}



