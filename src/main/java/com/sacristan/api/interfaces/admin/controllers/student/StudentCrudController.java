package com.sacristan.api.interfaces.admin.controllers.student;

import com.sacristan.api.interfaces.admin.dtos.student.StudentResponse;
import com.sacristan.api.interfaces.admin.dtos.user.CreateUser;
import com.sacristan.api.interfaces.admin.dtos.user.UpdateUser;
import com.sacristan.api.interfaces.admin.services.model.student.StudentCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/students")
@Tag(name = "Student CRUD controller", description = "CRUD Controller for basic Student entity")
public class StudentCrudController {

    private final StudentCrudService crudService;

    @Operation(
            summary = "Create Student",
            description = "Create a new Student entity"
    )
    @PostMapping
    public ResponseEntity<StudentResponse> create(
            @Valid @RequestBody(required = true) CreateUser createUser
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

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(
            @Parameter(
                    description = "ID of the User to be updated",
                    example = "1",
                    required = true
            )
            @PathVariable(required = true)  Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "UpdateUser DTO object containing the updated information for the User",
                    required = true,
                    content = @Content(
                            mediaType = "application/JSON",
                            schema = @Schema(implementation = UpdateUser.class),
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                    {
                                                        "name": "John",
                                                        "lastName": "Doe",
                                                        "email": "doe_john@testmail.com",
                                                        "username": "johndoe"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @Valid @RequestBody UpdateUser updateUser
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        StudentResponse
                                .of(crudService.update(id, updateUser.to()))
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
            Pageable pageable,
            @RequestParam(required = false) String q
    ) {
        return ResponseEntity.ok(
                crudService.list(pageable, q).map(StudentResponse::of)
        );
    }



}
