package com.sacristan.api.interfaces.admin.extra;

import com.sacristan.api.interfaces.admin.extra.dtos.ListCategoriesResponse;
import com.sacristan.api.interfaces.admin.extra.dtos.ReadCategoryResponse;
import com.sacristan.api.interfaces.shared.dtos.ReadUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/extras")
public class ExtraController {

    private final ExtraService extraService;


    @GetMapping("/users/{id}")
    public ResponseEntity<ReadUserResponse> readUser(@PathVariable Long id) {
        return ResponseEntity.ok(ReadUserResponse.ofEntity(extraService.getUser(id)));
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<ListCategoriesResponse>> listCategories(
            Pageable pageable
    ) {
        return ResponseEntity.ok(extraService.getCategories(pageable)
                .map(ListCategoriesResponse::ofEntity));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ReadCategoryResponse> readCategory(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(ReadCategoryResponse.ofEntity(extraService.getCategory(id)));
    }

}
