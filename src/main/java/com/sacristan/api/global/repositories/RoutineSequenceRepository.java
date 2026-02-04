package com.sacristan.api.global.repositories;

import com.sacristan.api.global.models.RoutineSequence;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineSequenceRepository extends JpaRepository<RoutineSequence, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM RoutineSequence r WHERE r.sequence.id = :sequenceId")
    void deleteBySequenceId(Long sequenceId);
}
