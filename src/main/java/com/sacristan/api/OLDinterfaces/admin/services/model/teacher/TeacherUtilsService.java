package com.sacristan.api.OLDinterfaces.admin.services.model.teacher;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TeacherUtilsService {

    private final TeacherRepository repository;

    public Teacher getReferenceById(Long id) {
        return repository.getReferenceById(id);
    }

    public Optional<Teacher> findById(Long idTeacher) {
        return repository.findById(idTeacher);
    }

    public Teacher save(Teacher teacher) {
        return repository.save(teacher);
    }

    public List<SortParamDTO> getTeacherSortParams() {
        return List.of(
                new SortParamDTO("Nombre", "user.name"),
                new SortParamDTO("Apellidos", "user.lastName"),
                new SortParamDTO("Email", "user.email"),
                new SortParamDTO("Usuario", "user.username")
        );
    }
}
