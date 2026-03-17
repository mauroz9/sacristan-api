package com.sacristan.api.global.entities.content.rotuine;

import com.sacristan.api.global.entities.content.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long>, JpaSpecificationExecutor<Routine> {

    boolean existsByCategory(Category category);
}
