package com.sacristan.api.global.entities.content.rotuine;

import com.sacristan.api.global.entities.content.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long>, JpaSpecificationExecutor<Routine> {

    boolean existsByCategory(Category category);

    @Query("SELECT r FROM Routine r WHERE r.user.id = :id")
    List<Routine> findByUserId(Long id);
}
