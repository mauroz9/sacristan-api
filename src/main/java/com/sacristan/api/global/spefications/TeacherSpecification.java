package com.sacristan.api.global.spefications;

import com.sacristan.api.global.models.user.Student;
import com.sacristan.api.global.models.user.Teacher;
import com.sacristan.api.global.models.user.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.PredicateSpecification;


public class TeacherSpecification {

    public static PredicateSpecification<Teacher> searchByTerm(String query) {

        return (from, cb) -> {
            if (query == null || query.isBlank()) {
                return cb.conjunction();
            }

            String likeQuery = "%" + query.toLowerCase() + "%";
            Join<Student, User> userJoin = from.join("user");

            return cb.or(
                    cb.like(cb.lower(userJoin.get("name")), likeQuery),
                    cb.like(cb.lower(userJoin.get("lastName")), likeQuery),
                    cb.like(cb.lower(userJoin.get("email")), likeQuery),
                    cb.like(cb.lower(userJoin.get("username")), likeQuery)
            );

        };

    }


}
