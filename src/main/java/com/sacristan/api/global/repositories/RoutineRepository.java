package com.sacristan.api.global.repositories;

import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long>, JpaSpecificationExecutor<Routine> {

    boolean existsByCategory(Category category);
}
