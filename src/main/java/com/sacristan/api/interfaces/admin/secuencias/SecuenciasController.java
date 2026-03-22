package com.sacristan.api.interfaces.admin.secuencias;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.interfaces.admin.secuencias.dtos.request.CreateSequenceRequest;
import com.sacristan.api.interfaces.admin.secuencias.dtos.request.UpdateSequenceRequest;
import com.sacristan.api.interfaces.admin.secuencias.dtos.response.SequenceDetailResponse;
import com.sacristan.api.interfaces.admin.secuencias.dtos.response.SequenceListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/secuencias")
public class SecuenciasController {

    private final SecuenciasService service;

    // * CRUD Operations
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody CreateSequenceRequest createRequest
    ) {
        service.create(createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<@Nullable SequenceDetailResponse> read(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                SequenceDetailResponse.ofEntity(service.getById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSequenceRequest updateRequest
    ) {
        service.update(id, updateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/duplicate")
    public ResponseEntity<SequenceDetailResponse> duplicate(
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SequenceDetailResponse.ofEntity(service.duplicate(id)));
    }

    @GetMapping
    public ResponseEntity<@Nullable Page<SequenceListResponse>> list(
            Pageable pageable,
            @RequestParam(required = false) String q
    ) {
        return ResponseEntity.ok(
                service.list(pageable, q).map(SequenceListResponse::ofEntity)
        );
    }

    @GetMapping("/sort-params")
    public ResponseEntity<@Nullable List<SortParamDTO>> getSequenceSortParams() {
        return ResponseEntity.ok(service.getSortParams());
    }
}
