package com.sacristan.api.interfaces.student.reproduccion;

import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.interfaces.student.reproduccion.dtos.request.EndReproductionRequest;
import com.sacristan.api.interfaces.student.reproduccion.dtos.response.SequenceDetailResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class ReproduccionController {

    private final ReproduccionService service;

    @GetMapping("/sequences/{id}")
    public ResponseEntity<@Nullable SequenceDetailResponse> getSequenceDetails(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(SequenceDetailResponse.ofEntity(service.getSequenceDetails(id)));
    }

    @PostMapping("/reproductions/{id}")
    public ResponseEntity<Long> startReproduction(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.startReproduction(id, user));
    }

    @PutMapping("/reproductions/{id}/end")
    public ResponseEntity<?> endReproduction(
            @PathVariable Long id,
            @RequestBody EndReproductionRequest request,
            @AuthenticationPrincipal User user
    ) {
        service.endReproduction(id, request, user);
        return ResponseEntity.ok().build();
    }
}
