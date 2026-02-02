package com.sacristan.api.global.controllers.teacher;

import com.sacristan.api.global.dtos.teacher.TeacherResponse;
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
@RequestMapping("/api/v1/teachers")
@Tag(name = "Teacher CRUD controller", description = "CRUD Controller for basic Teacher entity")
public class TeacherCrudController {

    public ResponseEntity<?> create() {
        return null;
    }

    public ResponseEntity<?> read() {
        return null;
    }

    public ResponseEntity<?> update() {
        return null;
    }

    public ResponseEntity<?> delete() {
        return null;
    }

    public ResponseEntity<Page<?>> list(
            Pageable pageable
    ) {

    }



}
