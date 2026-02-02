package com.sacristan.api.global.repositories;

import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Long>, JpaSpecificationExecutor<Sequence> {

    boolean existsByCategory(Category category);
}
