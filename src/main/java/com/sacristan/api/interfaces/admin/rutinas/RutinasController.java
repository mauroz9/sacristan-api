package com.sacristan.api.interfaces.admin.rutinas;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.interfaces.admin.rutinas.dtos.request.CreateRoutineRequest;
import com.sacristan.api.interfaces.admin.rutinas.dtos.request.CreateRoutineSegmentRequest;
import com.sacristan.api.interfaces.admin.rutinas.dtos.request.UpdateRoutineRequest;
import com.sacristan.api.interfaces.admin.rutinas.dtos.request.UpdateRoutineSegmentRequest;
import com.sacristan.api.interfaces.admin.rutinas.dtos.response.RoutineDetailResponse;
import com.sacristan.api.interfaces.admin.rutinas.dtos.response.RoutineListResponse;
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
@RequestMapping("/api/v1/admin/rutinas")
public class RutinasController {

    private final RutinasService service;

    // * CRUD Operations
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody CreateRoutineRequest createRequest
    ) {
        service.create(createRequest.toEntity(
                createRequest.sequences().stream()
                        .map(CreateRoutineSegmentRequest::toEntity)
                        .toList()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<@Nullable RoutineDetailResponse> read(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                RoutineDetailResponse.ofEntity(service.getById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRoutineRequest updateRequest
    ) {
        service.update(id, updateRequest.toEntity(
                updateRequest.sequences().stream()
                        .map(UpdateRoutineSegmentRequest::toEntity)
                        .toList()
        ));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // * LIST & SORT
    @GetMapping
    public ResponseEntity<@Nullable Page<RoutineListResponse>> list(
            Pageable pageable,
            @RequestParam(required = false) String q
    ) {
        return ResponseEntity.ok(
                service.list(pageable, q).map(RoutineListResponse::ofEntity)
        );
    }

    @GetMapping("/sort-params")
    public ResponseEntity<@Nullable List<SortParamDTO>> getRoutineSortParams() {
        return ResponseEntity.ok(service.getSortParams());
    }
}
