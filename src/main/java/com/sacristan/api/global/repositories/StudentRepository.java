package com.sacristan.api.global.repositories;

import com.sacristan.api.global.models.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    @Query("SELECT COUNT(s) FROM Student st " +
            "JOIN st.sequences s " +
            "WHERE st.id = :studentId")
    Long countSequencesByStudentId(@Param("studentId") Long studentId);

}
