package com.sacristan.api.interfaces.admin.alumnos;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.RoutineResponse;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.SequenceResponse;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.StudentListResponse;
import com.sacristan.api.interfaces.shared.dtos.CreateUserRequest;
import com.sacristan.api.interfaces.shared.dtos.UpdateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/alumnos")
public class AlumnoController { // Alumnos proviene del nombre de la interfaz, NO DE LA ENTIDAD

    private final AlumnoService service;

    // * CRUD
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody CreateUserRequest createUser
    ) {
        service.create(createUser.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest updateUser
    ) {
        service.update(id, updateUser.toEntity());
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
    public ResponseEntity<@Nullable Page<StudentListResponse>> list(
            Pageable pageable,
            @RequestParam(required = false) String q
    ) {
        return ResponseEntity.ok(service.list(pageable, q).map(s ->
                StudentListResponse.ofEntity(s,
                    service.countAssignedSequencesOfStudent(s.getId()),
                    service.countAssignedRoutinesOfStudent(s.getId()))
        ));
    }

    @GetMapping("/sort-params")
    public ResponseEntity<@Nullable List<SortParamDTO>> getStudentSortParams() {
        return ResponseEntity.ok(service.getSortParams());
    }

    // * SEQUENCE RELATIONSHIP
    @PostMapping("/{studentId}/sequences/{sequenceId}")
    public ResponseEntity<?> assignSequenceToStudent(
            @PathVariable Long studentId,
            @PathVariable Long sequenceId
    ) {
        service.assignSequenceToStudent(studentId, sequenceId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{studentId}/sequences/{sequenceId}")
    public ResponseEntity<?> unassignSequenceFromStudent(
            @PathVariable Long studentId,
            @PathVariable Long sequenceId
    ) {
        service.unassignSequenceFromStudent(studentId, sequenceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{studentId}/sequences")
    public ResponseEntity<@Nullable List<SequenceResponse>> getAssignedSequences(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(
                service.getSequencesOfStudent(studentId)
                        .stream()
                        .map(SequenceResponse::ofEntity)
                        .toList()
        );
    }

    @GetMapping("/{studentId}/sequences-available")
    public ResponseEntity<@Nullable List<SequenceResponse>> getUnassignedSequences(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(
                service.getUnassignedSequencesOfStudent(studentId)
                        .stream()
                        .map(SequenceResponse::ofEntity)
                        .toList()
        );
    }

    @GetMapping("/{studentId}/sequence-count")
    public ResponseEntity<@Nullable Integer> countAssignedSequences(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(service.countAssignedSequencesOfStudent(studentId));
    }

    // * ROUTINE RELATIONSHIP
    @PostMapping("/{studentId}/routines/{routineId}")
    public ResponseEntity<?> assignRoutineToStudent(
            @PathVariable Long studentId,
            @PathVariable Long routineId
    ) {
        service.assignRoutineToStudent(studentId, routineId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{studentId}/routines/{routineId}")
    public ResponseEntity<?> unassignRoutineFromStudent(
            @PathVariable Long studentId,
            @PathVariable Long routineId
    ) {
        service.unassignRoutineFromStudent(studentId, routineId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{studentId}/routines")
    public ResponseEntity<@Nullable Set<RoutineResponse>> getAssignedRoutines(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(
                service.getRoutinesOfStudent(studentId)
                        .stream()
                        .map(RoutineResponse::ofEntity)
                        .collect(java.util.stream.Collectors.toSet())
        );
    }

    @GetMapping("/{studentId}/routines-available")
    public ResponseEntity<@Nullable Set<RoutineResponse>> getUnassignedRoutines(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(
                service.getUnassignedRoutinesOfStudent(studentId)
                        .stream()
                        .map(RoutineResponse::ofEntity)
                        .collect(java.util.stream.Collectors.toSet())
        );
    }

    @GetMapping("/{studentId}/routine-count")
    public ResponseEntity<@Nullable Integer> countAssignedRoutines(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(service.countAssignedRoutinesOfStudent(studentId));
    }

}
