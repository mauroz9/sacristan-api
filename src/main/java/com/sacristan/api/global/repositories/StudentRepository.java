package com.sacristan.api.global.repositories;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.models.user.Teacher;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    @Query("SELECT SIZE(s.sequences) FROM Student s WHERE s.id = :id")
    int countSequencesByStudentId(Long id);

    List<Student> findByTeacher(Teacher teacher);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.teacher.id = :id")
    Integer getStudentCountByTeacherId(Long id);


    @EntityGraph(
            attributePaths = {
                    "sequences",
                    "sequences.steps",
                    "sequences.category"
            }
    )
    @Query(
            "SELECT s.sequences FROM Student s WHERE s.id = :id"
    )
    @Nullable List<Sequence> findAllSequencesByUserId(Long id);


}
