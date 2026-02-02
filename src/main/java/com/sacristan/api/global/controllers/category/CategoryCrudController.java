package com.sacristan.api.global.controllers.category;

import com.sacristan.api.global.dtos.category.CategoryResponse;
import com.sacristan.api.global.dtos.category.CreateCategory;
import com.sacristan.api.global.dtos.category.UpdateCategory;
import com.sacristan.api.global.services.category.CategoryCrudService;
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
@RequestMapping("/api/v1/categories")
@Tag(name = "Category CRUD controller", description = "CRUD Controller for basic Category entity")
public class CategoryCrudController {

    private final CategoryCrudService crudService;

    @Operation(
            summary = "Create Category",
            description = "Create a new Category entity"
    )
    @PostMapping
    public ResponseEntity<CategoryResponse> create(
            @RequestBody(required = true) CreateCategory createCategory
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        CategoryResponse.of(
                                crudService.create(
                                        createCategory.to()
                                )
                        )
                );
    }

    @Operation(
            summary = "Read Category",
            description = "Read the info of a specific category"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> read(
            @PathVariable(required = true) Long id
    ) {
        return ResponseEntity.ok(
                CategoryResponse.of(
                        crudService.read(id)
                )
        );
    }


    @Operation(
            summary = "Delete Category",
            description = "Delete a specific category"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        crudService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Update Category",
            description = "Update a specific category"
    )
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateCategory updateCategory
    ) {
        return ResponseEntity.ok(
                CategoryResponse.of(
                        crudService.update(id, updateCategory.to())
                )
        );
    }

    @Operation(
            summary = "List Categories",
            description = "List all categories with pagination"
    )
    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> list(
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                crudService.list(pageable).map(CategoryResponse::of)
        );
    }
}
