package com.sacristan.api.global.entities.content.sequence;

import com.sacristan.api.global.entities.content.category.Category;
import com.sacristan.api.interfaces.admin.dashboard.dtos.MostUsedSequencesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Long>, JpaSpecificationExecutor<Sequence> {

    boolean existsByCategory(Category category);

    long count();

    @Query(value = """
        SELECT COALESCE(COUNT(*), 0)
        FROM student_routines sr
        JOIN routine_days rd ON rd.routine_id = sr.routine_id
        JOIN routine_sequence rs ON rs.routine_id = sr.routine_id
        WHERE rd.day_of_the_week = :dayName
        """, nativeQuery = true)
    long countExpectedSequencesForDay(@Param("dayName") String dayName);

    @Query(value = """
        SELECT COALESCE(COUNT(*), 0)
        FROM reproductions r
        WHERE r.status = :completedStatus
          AND r.ended_at >= :dayStart
          AND r.ended_at < :dayEnd
        """, nativeQuery = true)
    long countCompletedSequencesForDay(
            @Param("dayStart") LocalDateTime dayStart,
            @Param("dayEnd") LocalDateTime dayEnd,
            @Param("completedStatus") String completedStatus
    );

    @Query("""
        SELECT new com.sacristan.api.interfaces.admin.dashboard.dtos.MostUsedSequencesDto(
            s.id,
            s.title,
            COUNT(r.id)
        )
        FROM Sequence s
        JOIN RoutineSegment rs ON rs.sequence = s
        JOIN Reproduction r ON r.routineSegment = rs
        GROUP BY s.id, s.title
        ORDER BY COUNT(r.id) DESC
        """)
    Page<MostUsedSequencesDto> findMostUsedSequences(Pageable pageable);
}
