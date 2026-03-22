package com.sacristan.api.global.entities.content.sequence;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.services.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SequenceModelService extends BaseServiceImpl<Sequence, Long, SequenceRepository> {

    public static List<SortParamDTO> getSortParams() {
        return List.of(
                new SortParamDTO("Título", "title"),
                new SortParamDTO("Descripción", "description"),
                new SortParamDTO("Categoría", "category.name")
        );
    }
}



