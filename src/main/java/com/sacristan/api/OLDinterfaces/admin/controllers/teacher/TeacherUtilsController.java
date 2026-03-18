package com.sacristan.api.OLDinterfaces.admin.controllers.teacher;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.interfaces.admin.services.mixed.StudentTeacherService;
import com.sacristan.api.interfaces.admin.services.model.teacher.TeacherUtilsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/teachers")
@Tag(
        name = "Teacher Utils controller",
        description = "Utils Controller for Teacher entity"
)
public class TeacherUtilsController {

    private final StudentTeacherService studentTeacherService;
    private final TeacherUtilsService service;

    @GetMapping("/{id}/student-count")
    @Operation(
            summary = "Get Teacher Student Count",
            description = "Get the number of students assigned to a specific teacher"
    )
    public ResponseEntity<Integer> getStudentCount(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(studentTeacherService.getStudentCountByTeacherId(id));
    }

    @GetMapping("/sort-params")
    @Operation(
            summary = "Get Teacher Sort Params",
            description = "Get the available sort parameters for the Teacher entity"
    )
    public ResponseEntity<List<SortParamDTO>> getTeacherSortParams() {
        return ResponseEntity.ok(service.getTeacherSortParams());
    }

}
