package com.sacristan.api.interfaces.student.home;

import com.sacristan.api.interfaces.student.home.dtos.response.RoutineListResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class HomeController {

    private final HomeService service;

    // * ROUTINES
    @GetMapping("/routines")
    public ResponseEntity<@Nullable Page<RoutineListResponse>> getRoutines(Pageable pageable) {
        return ResponseEntity.ok(service.getRoutines(pageable));
    }
}

