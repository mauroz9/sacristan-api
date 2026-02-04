package com.sacristan.api.interfaces.admin.controllers.sequence;

import com.sacristan.api.interfaces.admin.dtos.sequence.CreateSequence;
import com.sacristan.api.interfaces.admin.dtos.sequence.SequenceResponse;
import com.sacristan.api.interfaces.admin.services.sequence.SequenceLogicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sequences")
@Tag(name = "Sequence LOGIC controller", description = "CRUD Controller for logic Sequence entity")
public class SequenceLogicController {

    private final SequenceLogicService logicService;

    @Operation(
            summary = "Create Sequence",
            description = "Create a new Sequence entity"
    )
    @PostMapping("/{id}/duplicate")
    public ResponseEntity<SequenceResponse> create(
            @PathVariable(required = true) Long id
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
}
