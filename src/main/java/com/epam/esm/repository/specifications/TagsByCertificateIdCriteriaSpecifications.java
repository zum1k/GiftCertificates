package com.epam.esm.repository.specifications;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.CriteriaSpecification;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class TagsByCertificateIdCriteriaSpecifications implements CriteriaSpecification<Tag> {
    private final long certificateId;

    @Override
    public Predicate toPredicate(Root<Tag> root, CriteriaBuilder criteriaBuilder) {
        Join<Tag, GiftCertificate> join = root.join("certificates");
        return criteriaBuilder.equal(join.get("id"), certificateId);
    }
}
