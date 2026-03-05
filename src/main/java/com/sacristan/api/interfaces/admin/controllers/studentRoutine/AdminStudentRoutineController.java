package com.sacristan.api.interfaces.admin.controllers.studentRoutine;

import com.sacristan.api.interfaces.admin.dtos.routine.RoutineAssignResponse;
import com.sacristan.api.interfaces.admin.dtos.student.StudentResponse;
import com.sacristan.api.interfaces.admin.services.studentRoutine.AdminStudentRoutineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/students")
public class AdminStudentRoutineController {

    private final AdminStudentRoutineService service;

    @Operation(
            summary = "Assign Routine",
            description = "Assign a routine to a student"
    )
    @PostMapping("/{studentId}/routines/{routineId}")
    public ResponseEntity<StudentResponse> assign(
            @PathVariable Long studentId,
            @PathVariable Long routineId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(StudentResponse.of(service.assignRoutineToStudent(studentId, routineId)));
    }

    @Operation(
            summary = "Unassign Routine",
            description = "Unassign a routine to a student"
    )
    @DeleteMapping("/{studentId}/routines/{routineId}")
    public ResponseEntity<StudentResponse> unassign(
            @PathVariable Long studentId,
            @PathVariable Long routineId
    ) {
        return ResponseEntity.ok(StudentResponse.of(service.unassignRoutineFromStudent(studentId, routineId)));
    }

    @Operation(
            summary = "Get assigned Routines",
            description = "Get all the routines assigned to a student"
    )
    @GetMapping("/{studentId}/routines")
    public ResponseEntity<Set<RoutineAssignResponse>> getAssignedRoutines(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getRoutinesOfStudent(studentId)
                .stream()
                .map(RoutineAssignResponse::of)
                .collect(Collectors.toSet()));
    }

    @Operation(
            summary = "Get unassigned Routines",
            description = "Get all the routines not assigned to a student"
    )
    @GetMapping("/{studentId}/routines-available")
    public ResponseEntity<Set<RoutineAssignResponse>> getUnassignedRoutines(@PathVariable Long studentId){
        return ResponseEntity.ok(service
                .getUnassignedRoutinesOfStudent(studentId)
                .stream()
                .map(RoutineAssignResponse::of)
                .collect(Collectors.toSet()));
    }

}
