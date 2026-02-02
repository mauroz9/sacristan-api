package com.sacristan.api.global.repositories;

import com.sacristan.api.global.models.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {

    List<Step> findAllBySequenceIdOrderByOrderAsc(Long sequenceId);

    @Query("SELECT COALESCE(MAX(s.order), 0) FROM Step s WHERE s.sequence.id = :sequenceId")
    Integer findMaxOrderBySequenceId(Long sequenceId);
}
