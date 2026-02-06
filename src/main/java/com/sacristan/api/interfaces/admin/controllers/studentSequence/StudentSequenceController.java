package com.sacristan.api.interfaces.admin.controllers.studentSequence;

import com.sacristan.api.interfaces.admin.dtos.sequence.CreateSequence;
import com.sacristan.api.interfaces.admin.dtos.sequence.SequenceResponse;
import com.sacristan.api.interfaces.admin.dtos.student.StudentResponse;
import com.sacristan.api.interfaces.admin.services.studentSequence.StudentSequenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/students")
@Tag(name = "Student-Sequence controller", description = "Controller for managing the relationship between students and sequences")
public class StudentSequenceController {

    private final StudentSequenceService service;

    @Operation(
            summary = "Assign Sequence",
            description = "Assign a sequence to a student"
    )
    @PostMapping("/{studentId}/sequences/{sequenceId}")
    public ResponseEntity<StudentResponse> assign(
            @PathVariable Long studentId,
            @PathVariable Long sequenceId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(StudentResponse.of(service.assignSequenceToStudent(studentId, sequenceId)));
    }

    @Operation(
            summary = "Unassign Sequence",
            description = "Unassign a sequence to a student"
    )
    @DeleteMapping("/{studentId}/sequences/{sequenceId}")
    public ResponseEntity<StudentResponse> unassign(
            @PathVariable Long studentId,
            @PathVariable Long sequenceId
    ) {
        return ResponseEntity.ok(StudentResponse.of(service.unnassignSequenceFromStudent(studentId, sequenceId)));
    }

    @Operation(
            summary = "Get assigned Sequences",
            description = "Get all the sequences assigned to a student"
    )
    @GetMapping("/{studentId}/sequences")
    public ResponseEntity<List<SequenceResponse>> getAssignedSequences(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getSequencesOfStudent(studentId).stream().map(SequenceResponse::of).toList());
    }

    @Operation(
            summary = "Get unassigned Sequences",
            description = "Get all the sequences not assigned to a student"
    )
    @GetMapping("/{studentId}/sequences-available")
    public ResponseEntity<List<SequenceResponse>> getUnassignedSequences(@PathVariable Long studentId){
        return ResponseEntity.ok(service.getUnassignedSequencesOfStudent(studentId).stream().map(SequenceResponse::of).toList());
    }
    /*
    @Operation(
            summary = "Count assigned Sequences",
            description = "Count all the sequences assigned to a student"
    )
    @GetMapping("/{studentId}/sequence-count")
    public ResponseEntity<Long> countAssignedSequences(@PathVariable Long studentId){
        return ResponseEntity.ok(service.countAssignedSequencesOfStudent(studentId));
    }
    */

}
