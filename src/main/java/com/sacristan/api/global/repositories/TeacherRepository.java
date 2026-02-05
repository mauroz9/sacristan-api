package com.sacristan.api.global.repositories;

import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {

    @Query(
            "SELECT COUNT(t.students) FROM Teacher t WHERE t.id = :teacherId"
    )
    Integer countStudentsByTeacherId(Long teacherId);

}
