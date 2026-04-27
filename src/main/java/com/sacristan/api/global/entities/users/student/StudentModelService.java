package com.sacristan.api.global.entities.users.student;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.users.teacher.Teacher;
import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.services.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentModelService extends BaseServiceImpl<Student, Long, StudentRepository> {

    public int countByTeacher(Long teacherId) {
        return repository.getStudentCountByTeacherId(teacherId);
    }

    public List<Student> findByTeacher(Teacher teacher) {
        return repository.findByTeacher(teacher);
    }

    public long count() {
        return repository.count();
    }

    public static List<SortParamDTO> getSortParams() {
        return List.of(
                new SortParamDTO("Nombre" , "user.name"),
                new SortParamDTO("Apellidos", "user.lastName"),
                new SortParamDTO("Email", "user.email"),
                new SortParamDTO("Usuario", "user.username")
        );
    }

    public List<Routine> getRoutinesByUserId(User user) {
        return repository.getRoutinesByUserId(user.getId());
    }

    public Page<Sequence> findByFilters(Pageable pageable, Long id, Long categoryId, String search) {
        return repository.findFilteredSequencesByStudentId(
                id,
                categoryId,
                search,
                pageable
        );
    }

    public void deleteRoutineAssignmentsByRoutineId(Long id) {
        repository.deleteRoutineAssignmentsByRoutineId(id);
    }

    public void deleteSequenceAssignmentsBySequenceId(Long id) {
        repository.deleteSequenceAssignmentsBySequenceId(id);
    }
}



