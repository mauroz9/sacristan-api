package com.sacristan.api.interfaces.admin.controllers.teacher;

import com.sacristan.api.interfaces.admin.dtos.teacher.TeacherResponse;
import com.sacristan.api.interfaces.admin.dtos.user.CreateUser;
import com.sacristan.api.interfaces.admin.dtos.user.UpdateUser;
import com.sacristan.api.interfaces.admin.services.model.teacher.TeacherCrudService;
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
@RequestMapping("/api/v1/admin/teachers")
@Tag(name = "Teacher CRUD controller", description = "CRUD Controller for basic Teacher entity")
public class TeacherCrudController {

    private final TeacherCrudService crudService;

    @Operation(
            summary = "Create Teacher",
            description = "Create a new Teacher entity"
    )
    @PostMapping
    public ResponseEntity<TeacherResponse> create(
            @Valid @RequestBody(required = true) CreateUser createUser
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    TeacherResponse.of(
                            crudService.create(
                                    createUser.to()
                            )
                    )
                );
    }

    @Operation(
            summary = "Read Teacher",
            description = "Read the info of a specific teacher"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> read(
            @PathVariable(required = true) Long id
    ) {
        return ResponseEntity.ok(
                TeacherResponse.of(
                        crudService.read(id)
                )
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update Teacher",
            description = "Update the info of a specific teacher"
    )
    public ResponseEntity<TeacherResponse> update(
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
            @RequestBody UpdateUser updateUser
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        TeacherResponse
                                .of(crudService.update(id, updateUser.to()))
                );
    }



    @Operation(
            summary = "Delete Teacher",
            description = "Delete a specific teacher"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        crudService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "List Teachers",
            description = "List all teachers with pagination"
    )
    @GetMapping
    public ResponseEntity<Page<TeacherResponse>> list(
            Pageable pageable,
            @RequestParam(required = false) String q
    ) {
        return ResponseEntity.ok(
                crudService.list(pageable, q).map(TeacherResponse::of)
        );
    }



}
