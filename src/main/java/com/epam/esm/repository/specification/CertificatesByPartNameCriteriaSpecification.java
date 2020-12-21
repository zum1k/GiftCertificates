package com.epam.esm.repository.specification;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.CriteriaSpecification;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class CertificatesByPartNameCriteriaSpecification implements CriteriaSpecification<GiftCertificate> {
    private final String partName;

    @Override
    public Predicate toPredicate(Root<GiftCertificate> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("name"), "%" + partName + "%");
    }
}
