package com.epam.esm.repository.specification;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.CriteriaSpecification;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class TagsByNameCriteriaSpecifications implements CriteriaSpecification<Tag> {
    private final String tagName;

    @Override
    public Predicate toPredicate(Root<Tag> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("name"), tagName);
    }
}
