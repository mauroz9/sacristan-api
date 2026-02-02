package com.sacristan.api.global.controllers.step;

import com.sacristan.api.global.dtos.step.CreateStep;
import com.sacristan.api.global.dtos.step.StepResponse;
import com.sacristan.api.global.dtos.step.UpdateStep;
import com.sacristan.api.global.services.step.StepCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/steps")
@Tag(name = "Step CRUD controller", description = "CRUD Controller for Step entity")
public class StepCrudController {

    private final StepCrudService crudService;

    @Operation(
            summary = "List Steps",
            description = "List all steps or filter by sequence_id"
    )
    @GetMapping
    public ResponseEntity<List<StepResponse>> list(
            @RequestParam(required = false) Long sequenceId
    ) {
        List<StepResponse> steps;

        if (sequenceId != null) {
            steps = crudService.getStepsBySequence(sequenceId).stream()
                    .map(StepResponse::of)
                    .toList();
        } else {
            steps = crudService.getAll().stream()
                    .map(StepResponse::of)
                    .toList();
        }

        return ResponseEntity.ok(steps);
    }

    @Operation(
            summary = "Get Step by ID",
            description = "Get a specific step by its ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<StepResponse> read(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                StepResponse.of(crudService.getStepById(id))
        );
    }

    @Operation(
            summary = "Create Step",
            description = "Create a new step"
    )
    @PostMapping
    public ResponseEntity<StepResponse> create(
            @RequestBody CreateStep createStep
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        StepResponse.of(
                                crudService.create(createStep.to())
                        )
                );
    }

    @Operation(
            summary = "Update Step",
            description = "Update an existing step"
    )
    @PutMapping("/{id}")
    public ResponseEntity<StepResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateStep updateStep
    ) {
        return ResponseEntity.ok(
                StepResponse.of(
                        crudService.update(id, updateStep.to())
                )
        );
    }

    @Operation(
            summary = "Delete Step",
            description = "Delete a specific step"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        crudService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
