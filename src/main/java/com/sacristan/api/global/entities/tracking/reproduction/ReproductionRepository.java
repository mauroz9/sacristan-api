package com.sacristan.api.global.entities.tracking.reproduction;

import com.sacristan.api.global.entities.tracking.status.Status;
import com.sacristan.api.interfaces.admin.dashboard.dtos.LatestReproductionsDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReproductionRepository extends JpaRepository<Reproduction, Long>, JpaSpecificationExecutor<Reproduction> {

    @Query(
            "SELECT COUNT(r) FROM Reproduction r " +
            "WHERE r.startedAt >= :today AND r.startedAt < :tomorrow " +
            "AND r.status = :completed"
    )
    long countCompletedToday(LocalDateTime today, LocalDateTime tomorrow, Status completed);

    @Query(
            "SELECT COUNT(r) * 1.0 / (SELECT COUNT(s) FROM Student s) " +
                    "FROM Reproduction r " +
                    "WHERE r.startedAt >= :today AND r.startedAt < :tomorrow " +
                    "AND r.status = :completed"
    )
    double averageCompletedPerStudentToday(LocalDateTime today, LocalDateTime tomorrow, Status completed);

    @Query("""
        SELECT new com.sacristan.api.interfaces.admin.dashboard.dtos.LatestReproductionsDto(
            CONCAT(r.student.user.name, ' ', r.student.user.lastName),
            r.routineSegment.sequence.title,
            r.endedAt
        )
        FROM Reproduction r
        WHERE r.status = :status
        ORDER BY r.endedAt DESC
        """)
    Page<LatestReproductionsDto> findLatestReproductions(Pageable pageable, Status status);

    @Query(
            "SELECT r FROM Reproduction r " +
                    "WHERE r.student.id = :userId " +
                    "AND r.routineSegment.id = :routineSegmentId " +
                    "AND r.startedAt >= :today AND r.startedAt < :tomorrow " +
                    "AND r.status = :completed"
    )
    List<Reproduction> findCompletedRoutineSegmentForToday(Long userId, Long routineSegmentId, LocalDateTime today, LocalDateTime tomorrow, Status completed);

    @Modifying
    @Transactional
    @Query("DELETE FROM Reproduction r WHERE r.routineSegment.id = :id")
    void deleteByRoutineSegmentId(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Reproduction r WHERE r.student.id = :id")
    void deleteByStudentId(Long id);

    Integer countByStudentIdAndStatus(Long studentId, Status status);

    Reproduction findFirstByStudentIdOrderByStartedAtDesc(Long studentId);

    List<Reproduction> findByStudentIdAndStartedAtAfter(Long studentId, LocalDateTime date);

    @Query("SELECT DISTINCT CAST(r.startedAt AS date) FROM Reproduction r WHERE r.student.id = :studentId ORDER BY CAST(r.startedAt AS date) DESC")
    List<java.sql.Date> findDistinctReproductionDatesByStudentId(@Param("studentId") Long studentId);

    List<Reproduction> findByStudentId(Long studentId);

    Page<Reproduction> findByStudentIdOrderByStartedAtDesc(Long studentId, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reproduction r " +
            "WHERE r.student.id = :studentId " +
            "AND r.routineSegment.id = :segmentId " +
            "AND r.status = :status " +
            "AND CAST(r.endedAt AS date) = :today")
    boolean existsCompletedToday(
            @Param("studentId") Long studentId,
            @Param("segmentId") Long segmentId,
            @Param("status") Status status,
            @Param("today") LocalDate today
    );

    @Query("""
        SELECT DISTINCT r.routineSegment.id
        FROM Reproduction r
        WHERE r.student.id = :studentId
          AND r.status = :status
          AND r.endedAt IS NOT NULL
          AND CAST(r.endedAt AS date) = :today
        """)
    List<Long> findCompletedRoutineSegmentIdsForToday(@Param("studentId") Long studentId,
                                                     @Param("status") Status status,
                                                     @Param("today") LocalDate today);
}
