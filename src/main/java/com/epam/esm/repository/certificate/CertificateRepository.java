package com.epam.esm.repository.certificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.CriteriaSpecification;

import java.util.List;
import java.util.Optional;

public interface CertificateRepository   {
    Optional<GiftCertificate> add(GiftCertificate giftCertificate);

    Optional<GiftCertificate> remove(long id);

    Optional<GiftCertificate> update(GiftCertificate giftCertificate);

    List<GiftCertificate> findAllBySpecification(CriteriaSpecification<GiftCertificate> specification);

    List<GiftCertificate> findAll();

    Optional<GiftCertificate> findById(long id);


}
