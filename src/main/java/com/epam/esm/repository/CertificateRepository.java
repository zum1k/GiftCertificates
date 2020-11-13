package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CertificateRepository {
    Optional<GiftCertificate> add(GiftCertificate giftCertificate);

    Optional<GiftCertificate> remove(long id);

    Optional<GiftCertificate> update(GiftCertificate giftCertificate);

    List<GiftCertificate> findAllBySpecification(Specification specification);

    List<GiftCertificate> findAll();

    Optional<GiftCertificate> findById(long id);


}
