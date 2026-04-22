package com.sacristan.api.global.entities.content.rotuine;

import org.springframework.data.jpa.domain.PredicateSpecification;


public class RoutineSpecification {

    public static PredicateSpecification<Routine> searchByTerm(String query) {

        return (from, cb) -> {
            if (query == null || query.isBlank()) {
                return cb.conjunction();
            }

            String likeQuery = "%" + query.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(from.get("name")), likeQuery),
                    cb.like(cb.lower(from.get("category").get("name")), likeQuery)
            );

        };

    }


}
