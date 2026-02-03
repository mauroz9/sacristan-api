package com.sacristan.api.global.controllers.sequence;

import com.sacristan.api.global.dtos.sequence.CreateSequence;
import com.sacristan.api.global.dtos.sequence.SequenceResponse;
import com.sacristan.api.global.dtos.sequence.UpdateSequence;
import com.sacristan.api.global.services.sequence.SequenceCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sequences")
@Tag(name = "Sequence CRUD controller", description = "CRUD Controller for basic Sequence entity")
public class SequenceCrudController {

    private final SequenceCrudService crudService;

    @Operation(
            summary = "Create Sequence",
            description = "Create a new Sequence entity"
    )
    @PostMapping
    public ResponseEntity<SequenceResponse> create(
            @RequestBody(required = true) CreateSequence createSequence
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        SequenceResponse.of(
                                crudService.create(
                                        createSequence.to()
                                )
                        )
                );
    }

    @Operation(
            summary = "Read Sequence",
            description = "Read the info of a specific sequence"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SequenceResponse> read(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                SequenceResponse.of(
                        crudService.read(id)
                )
        );
    }

    @Operation(
            summary = "Update Sequence",
            description = "Update a specific sequence"
    )
    @PutMapping("/{id}")
    public ResponseEntity<SequenceResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateSequence updateSequence
    ) {
        return ResponseEntity.ok(
                SequenceResponse.of(
                        crudService.update(id, updateSequence.to())
                )
        );
    }

    @Operation(
            summary = "Delete Sequence",
            description = "Delete a specific sequence"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        crudService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "List Sequences",
            description = "List all sequences"
    )
    @GetMapping
    public ResponseEntity<List<SequenceResponse>> list() {
        return ResponseEntity.ok(
                crudService.getAll().stream()
                        .map(SequenceResponse::of)
                        .toList()
        );
    }
}
