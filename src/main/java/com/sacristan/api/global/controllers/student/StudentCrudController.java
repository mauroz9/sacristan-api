package com.sacristan.api.global.controllers.student;

import com.sacristan.api.global.dtos.student.StudentResponse;
import com.sacristan.api.global.dtos.user.CreateUser;
import com.sacristan.api.global.services.student.StudentCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@Tag(name = "Student CRUD controller", description = "CRUD Controller for basic Student entity")
public class StudentCrudController {

    private final StudentCrudService crudService;

    @Operation(
            summary = "Create Student",
            description = "Create a new Student entity"
    )
    @PostMapping
    public ResponseEntity<StudentResponse> create(
            @RequestBody(required = true) CreateUser createUser
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    StudentResponse.of(
                            crudService.create(
                                    createUser.to()
                            )
                    )
                );
    }

    @Operation(
            summary = "Read Student",
            description = "Read the info of a specific student"
    )
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> read(
            @PathVariable(required = true) Long id
    ) {
        return ResponseEntity.ok(
                StudentResponse.of(
                        crudService.read(id)
                )
        );
    }


    @Operation(
            summary = "Delete Student",
            description = "Delete a specific student"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        crudService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "List Students",
            description = "List all students with pagination"
    )
    @GetMapping
    public ResponseEntity<Page<StudentResponse>> list(
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                crudService.list(pageable).map(StudentResponse::of)
        );
    }



}
