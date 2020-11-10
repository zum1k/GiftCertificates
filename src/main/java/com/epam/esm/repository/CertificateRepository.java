package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CertificateRepository {
    GiftCertificate add(GiftCertificate giftCertificate);

    GiftCertificate remove(long id);

    GiftCertificate update(long certificateId, GiftCertificate giftCertificate);

    List<GiftCertificate> findAll(Specification specification);

    GiftCertificate findById(long id);
}
