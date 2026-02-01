package com.sacristan.api.global.controllers.user;

import com.sacristan.api.global.dtos.user.CreateUser;
import com.sacristan.api.global.dtos.user.UpdateUser;
import com.sacristan.api.global.dtos.user.UserResponse;
import com.sacristan.api.global.services.user.UserCrudService;
import com.sacristan.api.global.services.user.UserUtilsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User CRUD controller", description = "CRUD Controller for basic User entity")
public class UserCrudController {

    private final UserCrudService service;
    private final UserUtilsService utilsService;


    @PostMapping
    @ApiResponse(
            responseCode = "201",
            description = "User created successfully",
            content = @Content(
                    mediaType = "application/JSON",
                    schema = @Schema(implementation = UserResponse.class),
                    examples = {
                            @ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "name": "John",
                                                "lastName": "Doe",
                                            }
                                            """
                            )
                    }
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Illegal Arguments on request",
            content = @Content(
                    mediaType = "application/JSON",
                    examples = {
                            @ExampleObject(
                                    value = """
                                            {
                                              "type": "about:blank",
                                              "title": "Passwords don't match",
                                              "status": 400,
                                              "detail": "Passwords provided in 'password' and 'verifyPassword' fields do not match.",
                                              "instance": "/api/v1/user"
                                            }
                                            """
                            )
                    }
            )
    )
    @Operation(
            summary = "Create a new User",
            description = "Creates a new User in the system with the provided information."
    )
    public ResponseEntity<UserResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "CreateUser DTO object containing the information needed to create a new User",
                    required = true,
                    content = @Content(
                            mediaType = "application/JSON",
                            schema = @Schema(implementation = CreateUser.class),
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                    {
                                                        "name": "John",
                                                        "lastName": "Doe",
                                                        "email": "doe_john@testmail.com",
                                                        "username": "johndoe",
                                                        "password": "password123",
                                                        "verifyPassword": "password123"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @RequestBody(required = true) CreateUser createUser
    ) {

        if (!utilsService.passwordMatch(createUser.password(), createUser.verifyPassword()))
            throw new IllegalArgumentException("Passwords don't match");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    UserResponse.of(service.create(createUser.to()))
                );
    }


    @GetMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "User retrieved successfully",
            content = @Content(
                    mediaType = "application/JSON",
                    schema = @Schema(implementation = UserResponse.class),
                    examples = {
                            @ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "name": "John",
                                                "lastName": "Doe",
                                            }
                                            """
                            )
                    }
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found",
            content = @Content(
                    mediaType = "application/JSON",
                    examples = {
                            @ExampleObject(
                                    value = """
                                            {
                                              "type": "about:blank",
                                              "title": "User Not Found",
                                              "status": 404,
                                              "detail": "No User found with ID 99",
                                              "instance": "/api/v1/user/99"
                                            }
                                            """
                            )
                    }
            )
    )
    @Operation(
            summary = "Read a User by ID",
            description = "Retrieves the User information for the given User ID."
    )
    public ResponseEntity<UserResponse> read(
            @Parameter(
                    description = "ID of the User to be retrieved",
                    example = "1",
                    required = true
            )
            @PathVariable(required = true) Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    UserResponse.of(service.read(id))
                );
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable  Long id,
            @RequestBody UpdateUser updateUser
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    UserResponse.of(service.update(id, updateUser.to()))
                );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable  Long id
    ) {

        service.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


    @GetMapping
    public ResponseEntity<Page<UserResponse>> list(Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    service.list(pageable).map(UserResponse::of)
                );
    }
}
