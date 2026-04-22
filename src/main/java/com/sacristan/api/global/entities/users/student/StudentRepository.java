package com.sacristan.api.global.entities.users.student;

import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.users.teacher.Teacher;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @EntityGraph(
            attributePaths = {
                    "routines",
            }
    )
    @Query(
            "SELECT s.routines FROM Student s WHERE s.id = :id"
    )
    List<Routine> findAllRoutinesByUserId(Long id);

    @Modifying
    @Query(value = "DELETE FROM student_routines WHERE routine_id = :routineId", nativeQuery = true)
    void deleteRoutineAssociationsByRoutineId(Long routineId);

    @Modifying
    @Query(
            value = "DELETE FROM STUDENTS_SEQUENCES WHERE sequences_id = :id", nativeQuery = true
    )
    void deleteSequenceFromStudents(Long id);

    @EntityGraph(attributePaths = {"sequences.steps", "sequences.category"})
    @Query(
            "SELECT seq FROM Student st JOIN st.sequences seq " +
                    "WHERE st.id = :userId " +
                    "AND (:categoryId IS NULL OR seq.category.id = :categoryId) " +
                    "AND (:searchQuery IS NULL OR " +
                    "LOWER(seq.title) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR " +
                    "LOWER(seq.description) LIKE LOWER(CONCAT('%', :searchQuery, '%')))"
    )
    Page<Sequence> findFilteredSequencesByStudentId(
            @Param("userId") Long userId,
            @Param("categoryId") Long categoryId,
            @Param("searchQuery") String searchQuery,
            Pageable pageable
    );

    @Query("SELECT s.routines FROM Student s WHERE s.id = :id")
    List<Routine> getRoutinesByUserId(Long id);
}
