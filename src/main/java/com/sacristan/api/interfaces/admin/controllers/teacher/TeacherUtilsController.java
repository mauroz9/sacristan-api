package com.sacristan.api.interfaces.admin.controllers.teacher;

import com.sacristan.api.interfaces.admin.services.teacher.TeacherUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/teachers")
public class TeacherUtilsController {

    private final TeacherUtilsService service;

    @GetMapping("/{id}/student-count")
    public ResponseEntity<Integer> getStudentCount(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getStudentCount(id));
    }

}
