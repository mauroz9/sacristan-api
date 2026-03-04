package com.sacristan.api.global.repositories;

import com.sacristan.api.global.models.Reproduction;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.Status;
import com.sacristan.api.global.models.user.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReproductionRepository extends JpaRepository<Reproduction, Long>, JpaSpecificationExecutor<Reproduction> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Reproduction r WHERE r.routineSequence.id = :routineSequenceId")
    void deleteByRoutineSequenceId(Long routineSequenceId);


    @Query(
            "SELECT r FROM Reproduction r " +
            "WHERE r.student.id = :userId " +
            "AND r.routineSequence.id = :routineSequenceId " +
            "AND r.startedAt >= :today AND r.startedAt < :tomorrow " +
            "AND r.status = :completed"
    )
    List<Reproduction> findCompletedRoutineSequenceForToday(Long userId, Long routineSequenceId,
                                                            LocalDateTime today,
                                                            LocalDateTime tomorrow,
                                                            Status completed);
}
