package com.sacristan.api.global.entities.assignments.routineSegment;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineSegmentRepository extends JpaRepository<RoutineSegment, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM RoutineSequence r WHERE r.sequence.id = :sequenceId")
    void deleteBySequenceId(Long sequenceId);
}
