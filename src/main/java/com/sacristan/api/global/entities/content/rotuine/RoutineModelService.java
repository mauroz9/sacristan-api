package com.sacristan.api.global.entities.content.rotuine;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.services.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineModelService extends BaseServiceImpl<Routine, Long, RoutineRepository> {
    public static List<SortParamDTO> getSortParams() {
        return List.of(
                new SortParamDTO("Nombre", "name"),
                new SortParamDTO("Categoría", "category.name")
        );
    }
}
