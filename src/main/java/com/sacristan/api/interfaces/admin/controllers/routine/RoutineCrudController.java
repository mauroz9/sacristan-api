package com.sacristan.api.interfaces.admin.controllers.routine;

import com.sacristan.api.interfaces.admin.dtos.routine.CreateRoutine;
import com.sacristan.api.interfaces.admin.dtos.routine.RoutineResponse;
import com.sacristan.api.interfaces.admin.dtos.routine.UpdateRoutine;
import com.sacristan.api.interfaces.admin.services.routine.RoutineCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/routines")
@Tag(name = "Routine CRUD controller", description = "CRUD Controller for basic Routine entity")
public class RoutineCrudController {

    private final RoutineCrudService crudService;

    @Operation(
            summary = "Create Routine",
            description = "Create a new Routine entity"
    )
    @PostMapping
    public ResponseEntity<RoutineResponse> create(
            @RequestBody CreateRoutine createRoutine
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        RoutineResponse.of(
                                crudService.create(
                                        createRoutine.to()
                                )
                        )
                );
    }

    @Operation(
            summary = "Read Routine",
            description = "Read the info of a specific routine"
    )
    @GetMapping("/{id}")
    public ResponseEntity<RoutineResponse> read(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                RoutineResponse.of(
                        crudService.read(id)
                )
        );
    }

    @Operation(
            summary = "Update Routine",
            description = "Update a specific routine"
    )
    @PutMapping("/{id}")
    public ResponseEntity<RoutineResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateRoutine updateRoutine
    ) {
        return ResponseEntity.ok(
                RoutineResponse.of(
                        crudService.update(id, updateRoutine.to())
                )
        );
    }

    @Operation(
            summary = "Delete Routine",
            description = "Delete a specific routine"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        crudService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "List Routines",
            description = "List all routines with pagination"
    )
    @GetMapping
    public ResponseEntity<Page<RoutineResponse>> list(
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                crudService.list(pageable).map(RoutineResponse::of)
        );
    }
}
