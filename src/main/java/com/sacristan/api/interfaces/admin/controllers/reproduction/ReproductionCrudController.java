package com.sacristan.api.interfaces.admin.controllers.reproduction;

import com.sacristan.api.interfaces.admin.dtos.reproduction.CreateReproduction;
import com.sacristan.api.interfaces.admin.dtos.reproduction.ReproductionResponse;
import com.sacristan.api.interfaces.admin.dtos.reproduction.UpdateReproduction;
import com.sacristan.api.interfaces.admin.services.reproduction.ReproductionCrudService;
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
@RequestMapping("/api/reproductions")
@Tag(name = "Reproduction CRUD controller", description = "CRUD Controller for basic Reproduction entity")
public class ReproductionCrudController {

    private final ReproductionCrudService crudService;

    @Operation(
            summary = "Create Reproduction",
            description = "Create a new Reproduction entity"
    )
    @PostMapping
    public ResponseEntity<ReproductionResponse> create(
            @RequestBody CreateReproduction createReproduction
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ReproductionResponse.of(
                                crudService.create(
                                        createReproduction.to()
                                )
                        )
                );
    }

    @Operation(
            summary = "Read Reproduction",
            description = "Read the info of a specific reproduction"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ReproductionResponse> read(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                ReproductionResponse.of(
                        crudService.read(id)
                )
        );
    }

    @Operation(
            summary = "Update Reproduction",
            description = "Update a specific reproduction"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ReproductionResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateReproduction updateReproduction
    ) {
        return ResponseEntity.ok(
                ReproductionResponse.of(
                        crudService.update(id, updateReproduction.to())
                )
        );
    }

    @Operation(
            summary = "Delete Reproduction",
            description = "Delete a specific reproduction"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        crudService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "List Reproductions",
            description = "List all reproductions with pagination"
    )
    @GetMapping
    public ResponseEntity<Page<ReproductionResponse>> list(
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                crudService.list(pageable).map(ReproductionResponse::of)
        );
    }
}
