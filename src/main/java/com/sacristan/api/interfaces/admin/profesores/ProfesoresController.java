package com.sacristan.api.interfaces.admin.profesores;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.users.teacher.Teacher;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.ReadStudentResponse;
import com.sacristan.api.interfaces.admin.profesores.dtos.response.AssignedStudentResponse;
import com.sacristan.api.interfaces.admin.profesores.dtos.response.ReadTeacherResponse;
import com.sacristan.api.interfaces.admin.profesores.dtos.response.UnAssignedStudentResponse;
import com.sacristan.api.interfaces.shared.dtos.CreateUserRequest;
import com.sacristan.api.interfaces.shared.dtos.UpdateUserRequest;
import com.sacristan.api.interfaces.admin.profesores.dtos.response.TeacherListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/profesores")
public class ProfesoresController {

    private final ProfesoresService service;

    // * CRUD
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody CreateUserRequest createUser
    ) {
        Teacher teacher = Teacher.builder()
                .user(createUser.toEntity())
                .build();
        service.create(teacher);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<@Nullable ReadTeacherResponse> read(
            @PathVariable Long id
    ) {
        Teacher teacher = service.getById(id);
        return ResponseEntity.ok(
                ReadTeacherResponse.ofEntity(teacher)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest updateUser
    ) {
        service.update(id, updateUser.toEntity());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // * EXTRA
    @GetMapping
    public ResponseEntity<@Nullable Page<TeacherListResponse>> list(
            Pageable pageable,
            @RequestParam(required = false) String q
    ) {
        return ResponseEntity.ok(service.list(pageable, q).map(t ->
                TeacherListResponse.ofEntity(t, service.getStudentCountByTeacherId(t.getId()))
        ));
    }

    @GetMapping("/{id}/student-count")
    public ResponseEntity<@Nullable Integer> getStudentCount(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getStudentCountByTeacherId(id));
    }

    @GetMapping("/sort-params")
    public ResponseEntity<@Nullable List<SortParamDTO>> getTeacherSortParams() {
        return ResponseEntity.ok(service.getSortParams());
    }

    // * Relación Alumno - Profesor
    @PutMapping("/{teacherId}/students/{studentId}")
    public ResponseEntity<AssignedStudentResponse> assignStudentToTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(AssignedStudentResponse
                .ofEntity(service.assignTeacher(studentId, teacherId)));
    }

    @DeleteMapping("/{teacherId}/students/{studentId}")
    public ResponseEntity<UnAssignedStudentResponse> unassignStudentFromTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(UnAssignedStudentResponse
                .ofEntity(service.unassignTeacher(studentId)));
    }

    @GetMapping("/students/unassigned")
    public ResponseEntity<@Nullable List<UnAssignedStudentResponse>> getStudentsWithoutTeacher() {
        return ResponseEntity.ok(
                service.getStudentsByTeacher(null)
                        .stream()
                        .map(UnAssignedStudentResponse::ofEntity)
                        .toList()
        );
    }

    @GetMapping("/{teacherId}/students")
    public ResponseEntity<@Nullable List<AssignedStudentResponse>> getStudentsByTeacher(
            @PathVariable Long teacherId
    ) {
        return ResponseEntity.ok(
                service.getStudentsByTeacher(teacherId)
                        .stream()
                        .map(AssignedStudentResponse::ofEntity)
                        .toList()
        );
    }

}
