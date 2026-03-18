package com.sacristan.api.OLDinterfaces.admin.controllers.sequence;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.interfaces.admin.dtos.sequence.SequenceResponse;
import com.sacristan.api.interfaces.admin.services.model.sequence.SequenceLogicService;
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
@Tag(name = "Sequence LOGIC controller", description = "CRUD Controller for business logic Sequence entity")
public class SequenceLogicController {

    private final SequenceLogicService logicService;

    @Operation(
            summary = "Duplicate Sequence",
            description = "Create a duplicate of an existing Sequence entity"
    )
    @PostMapping("/{id}/duplicate")
    public ResponseEntity<SequenceResponse> duplicate(
            @PathVariable Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        SequenceResponse.of(
                                logicService.duplicate(
                                        id
                                )
                        )
                );
    }

    @GetMapping("/sort-params")
    @Operation(
            summary = "Get Sequence Sort Params",
            description = "Get the available sort parameters for the Sequence entity"
    )
    public ResponseEntity<List<SortParamDTO>> getSequenceSortParams() {
        return ResponseEntity.ok(logicService.getSequenceSortParams());
    }
}
