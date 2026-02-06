package com.sacristan.api.interfaces.admin.controllers.student;

import com.sacristan.api.interfaces.admin.services.student.StudentUtilsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "Student Utils controller",
        description = "Utils Controller for Student entity"
)
@RequestMapping("/api/v1/admin/students")
public class StudentUtilsController {

    private final StudentUtilsService service;


    @GetMapping("/{id}/sequence-count")
    @Operation(
            summary = "Get Student Sequence Count",
            description = "Get the number of sequences assigned to a specific student"
    )
    public ResponseEntity<Long> getSequenceCount(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getSequenceCount(id));
    }


}
