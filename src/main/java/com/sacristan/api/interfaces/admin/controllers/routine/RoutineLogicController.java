package com.sacristan.api.interfaces.admin.controllers.routine;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.interfaces.admin.services.model.routine.RoutineLogicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines")
@Tag(name = "Routine CRUD controller", description = "CRUD Controller for business logic Routine entity")
public class RoutineLogicController {

    private final RoutineLogicService logicService;

    @GetMapping("/sort-params")
    @Operation(
            summary = "Get Routine Sort Params",
            description = "Get the available sort parameters for the Routine entity"
    )
    public ResponseEntity<List<SortParamDTO>> getRoutineSortParams() {
        return ResponseEntity.ok(logicService.getRoutineSortParams());
    }
}
