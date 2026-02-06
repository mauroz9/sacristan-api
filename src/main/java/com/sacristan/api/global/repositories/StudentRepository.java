package com.sacristan.api.global.repositories;

import com.sacristan.api.global.models.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {


    @Query("SELECT SIZE(s.sequences) FROM Student s WHERE s.id = :id")
    int countSequencesByStudentId(Long id);


}
