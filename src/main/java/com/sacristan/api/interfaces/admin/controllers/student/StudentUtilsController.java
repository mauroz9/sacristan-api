package com.sacristan.api.interfaces.admin.controllers.student;

import com.sacristan.api.interfaces.admin.dtos.student.StudentResponse;
import com.sacristan.api.interfaces.admin.services.mixed.StudentTeacherService;
import com.sacristan.api.interfaces.admin.services.model.student.StudentUtilsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "Student Utils controller",
        description = "Utils Controller for Student entity"
)
@RequestMapping("/api/v1/admin/students")
public class StudentUtilsController {

    private final StudentUtilsService service;
    private final StudentTeacherService studentTeacherService;


    @GetMapping("/{id}/sequence-count")
    @Operation(
            summary = "Get Student Sequence Count",
            description = "Get the number of sequences assigned to a specific student"
    )
    public ResponseEntity<Integer> getSequenceCount(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getSequenceCount(id));
    }

    @GetMapping("/no-teacher")
    @Operation(
            summary = "Get Students without Teacher",
            description = "Get the students that are not assigned to any teacher"
    )
    public ResponseEntity<List<StudentResponse>> getStudentsWithoutTeacher() {
        return ResponseEntity.ok(
                studentTeacherService.getStudentsWithTeacher(null)
                        .stream()
                        .map(StudentResponse::of)
                        .toList()
        );
    }

    @GetMapping("/teacher/{id}")
    @Operation(
            summary = "Get Students with specified Teacher",
            description = "Get the students that are assigned to the teacher with the specified id"
    )
    public ResponseEntity<List<StudentResponse>> getStudentsWithTeacher(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                studentTeacherService.getStudentsWithTeacher(
                        id)
                        .stream()
                        .map(StudentResponse::of)
                        .toList()
        );
    }

    @PutMapping("/{id}/unassign-teacher")
    @Operation(
            summary = "Unassign Teacher from Student",
            description = "Unassign the teacher from the student with the specified id"
    )
    public ResponseEntity<StudentResponse> unassignTeacher(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                StudentResponse.of(studentTeacherService.unassignTeacher(id))
        );
    }

    @PutMapping("/{studentId}/assign-teacher/{teacherId}")
    @Operation(
            summary = "Assign Teacher for Student",
            description = "Assign the teacher for the student with the specified id"
    )
    public ResponseEntity<StudentResponse> assignTeacher(
            @PathVariable Long studentId,
            @PathVariable Long teacherId
    ){
        return ResponseEntity.ok(
                StudentResponse.of(studentTeacherService.assignTeacher(studentId, teacherId))
        );
    }



}
