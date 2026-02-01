package com.sacristan.api.global.controllers.user;

import com.sacristan.api.global.dtos.user.CreateUser;
import com.sacristan.api.global.dtos.user.UpdateUser;
import com.sacristan.api.global.dtos.user.UserResponse;
import com.sacristan.api.global.services.user.UserCrudService;
import com.sacristan.api.global.services.user.UserUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserCrudController {

    private final UserCrudService service;
    private final UserUtilsService utilsService;


    @PostMapping
    public ResponseEntity<UserResponse> create(
            @RequestBody CreateUser createUser
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
    public ResponseEntity<UserResponse> read(
            @PathVariable Long id
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
