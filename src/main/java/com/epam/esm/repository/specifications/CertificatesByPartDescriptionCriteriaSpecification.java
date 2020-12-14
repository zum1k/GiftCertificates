package com.epam.esm.repository.specifications;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.CriteriaSpecification;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class CertificatesByPartDescriptionCriteriaSpecification implements CriteriaSpecification<GiftCertificate> {
    private final String descriptionPart;

    @Override
    public Predicate toPredicate(Root<GiftCertificate> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("description"), "%" + descriptionPart + "%");
    }
}
