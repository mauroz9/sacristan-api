package com.sacristan.api.interfaces.admin.services.model.routine;

import com.sacristan.api.global.dtos.SortParamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineLogicService {

    public List<SortParamDTO> getRoutineSortParams() {
        return List.of(
                new SortParamDTO("Título" , "name"),
                //new SortParamDTO("Descripción", "description"),
                new SortParamDTO("Categoría", "category.name")
        );
    }
}
