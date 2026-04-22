package com.sacristan.api.global.entities.content.sequence;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.PredicateSpecification;

public class SequenceSpecification {

    public static PredicateSpecification<Sequence> searchByTerm(String query) {
        return (from, cb) -> {
            if (query == null || query.isBlank()) {
                return cb.conjunction();
            }
            return buildTermPredicate(query, from, cb);
        };
    }

    public static PredicateSpecification<Sequence> searchByFilters(Long categoryId, String search, Long userID) {
        return (from, cb) -> {
            var predicates = cb.conjunction();

            if (categoryId != null) {
                predicates = cb.and(predicates, cb.equal(from.get("category").get("id"), categoryId));
            }

            if (search != null && !search.isBlank()) {
                predicates = cb.and(predicates, buildTermPredicate(search, from, cb));
            }

            if (userID != null) {
                predicates = cb.and(predicates, cb.equal(from.join("assignedUsers").get("id"), userID));
            }

            return predicates;
        };
    }

    private static Predicate buildTermPredicate(String query, From<?, Sequence> from, CriteriaBuilder cb) {
        String likeQuery = "%" + query.toLowerCase() + "%";
        return cb.or(
                cb.like(cb.lower(from.get("title")), likeQuery),
                cb.like(cb.lower(from.get("description")), likeQuery),
                cb.like(cb.lower(from.get("category").get("name")), likeQuery)
        );
    }
}
