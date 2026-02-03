package com.sacristan.api.global.repositories;

import com.sacristan.api.global.models.Reproduction;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReproductionRepository extends JpaRepository<Reproduction, Long>, JpaSpecificationExecutor<Reproduction> {

    List<Reproduction> findByStudent(Student student);

    List<Reproduction> findBySequence(Sequence sequence);

    List<Reproduction> findByStudentAndSequence(Student student, Sequence sequence);
}
