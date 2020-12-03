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
public class CertificatesByTagNameCriteriaSpecification implements CriteriaSpecification<GiftCertificate> {
    private final String tagName;

    @Override
    public Predicate toPredicate(Root<GiftCertificate> root, CriteriaBuilder criteriaBuilder) {
        Join<Tag, GiftCertificate> join = root.join("tags");
        return criteriaBuilder.equal(join.get("name"), tagName);
    }
}



