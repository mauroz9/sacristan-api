package com.sacristan.api.global.entities.tracking.reproduction;

import com.sacristan.api.global.entities.tracking.status.Status;
import com.sacristan.api.interfaces.admin.dashboard.dtos.LatestReproductionsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
