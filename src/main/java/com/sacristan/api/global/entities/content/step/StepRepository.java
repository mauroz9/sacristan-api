package com.sacristan.api.global.entities.content.step;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Step, Long>, JpaSpecificationExecutor<Step> {

    List<Step> findAllBySequenceIdOrderByPositionAsc(Long sequenceId);

    @Query("SELECT COALESCE(MAX(s.position), 0) FROM Step s WHERE s.sequence.id = :sequenceId")
    Integer findMaxOrderBySequenceId(Long sequenceId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Step s WHERE s.sequence.id = :id")
    void deleteBySequenceId(Long id);
}
