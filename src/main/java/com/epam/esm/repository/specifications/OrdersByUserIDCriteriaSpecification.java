package com.epam.esm.repository.specifications;

import com.epam.esm.entity.Order;
import com.epam.esm.repository.CriteriaSpecification;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class OrdersByUserIDCriteriaSpecification implements CriteriaSpecification<Order> {
    private final long userId;

    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("user_id"), userId);
    }
}