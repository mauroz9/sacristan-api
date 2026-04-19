package com.sacristan.api.interfaces.student.reproduccion;

import com.sacristan.api.interfaces.student.reproduccion.dtos.response.SequenceDetailResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class ReproduccionController {

    private final ReproduccionService service;

    // * SEQUENCES
    @GetMapping("/sequences/{id}")
    public ResponseEntity<@Nullable SequenceDetailResponse> getSequenceDetails(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSequenceDetails(id));
    }

    // * REPRODUCTIONS
    @PostMapping("/reproductions/{id}")
    public ResponseEntity<?> startReproduction(@PathVariable Long id) {
        return ResponseEntity.ok(service.startReproduction(id));
    }

    @PutMapping("/reproductions/{id}/end")
    public ResponseEntity<?> endReproduction(@PathVariable Long id) {
        service.endReproduction(id);
        return ResponseEntity.ok().build();
    }
}
