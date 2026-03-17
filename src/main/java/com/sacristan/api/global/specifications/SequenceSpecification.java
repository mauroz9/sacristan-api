package com.sacristan.api.global.specifications;

import com.sacristan.api.global.entities.content.sequence.Sequence;
import org.springframework.data.jpa.domain.PredicateSpecification;

public class SequenceSpecification {

    public static PredicateSpecification<Sequence> searchByTerm(String query) {

        return (from, cb) -> {
            if (query == null || query.isBlank()) {
                return cb.conjunction();
            }

            String likeQuery = "%" + query.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(from.get("title")), likeQuery),
                    cb.like(cb.lower(from.get("description")), likeQuery),
                    cb.like(cb.lower(from.get("category").get("name")), likeQuery)
            );

        };

    }
}
