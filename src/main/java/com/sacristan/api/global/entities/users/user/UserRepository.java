package com.sacristan.api.global.entities.users.user;


import com.sacristan.api.global.entities.users.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUsernameIgnoreCase(String username);

    @Query("SELECT COUNT(s) > 0 FROM Student s WHERE s.teacher = :teacher")
    boolean existsStudentsAssignedToTeacher(Teacher teacher);

    @Query(
            "SELECT Count(u) > 0 FROM User u Where Lower(u.email) = Lower(:email) AND u.id <> :id"
    )
    boolean existsByEmailAndIdNot(String email, Long id);


    @Query(
            "SELECT Count(u) > 0 FROM User u Where Lower(u.username) = Lower(:s) AND u.id <> :id"
    )
    boolean existsByUsernameIgnoreCaseAndIdNot(String s, Long id);

    Optional<User> findByEmail(String email);
}