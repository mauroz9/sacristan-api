package com.sacristan.api.OLDinterfaces.admin.controllers.reproduction;

import com.sacristan.api.interfaces.admin.dtos.reproduction.ReproductionResponse;
import com.sacristan.api.interfaces.admin.services.model.reproduction.ReproductionCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reproductions")
@Tag(name = "Reproduction CRUD controller", description = "CRUD Controller for basic Reproduction entity")
public class ReproductionCrudController {

    private final ReproductionCrudService crudService;

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
