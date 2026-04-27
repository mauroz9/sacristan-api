package com.sacristan.api.global.entities.assignments.routineSegment;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineSegmentRepository extends JpaRepository<RoutineSegment, Long>, JpaSpecificationExecutor<RoutineSegment> {

    @Modifying
    @Transactional
    @Query("DELETE FROM RoutineSegment r WHERE r.sequence.id = :sequenceId")
    void deleteBySequenceId(Long sequenceId);

    @Modifying
    @Transactional
    @Query("DELETE FROM RoutineSegment r WHERE r.routine.id = :routineId")
    void deleteByRoutineId(@Param("routineId") Long routineId);

    List<RoutineSegment> findByRoutineId(Long routineId);
}
