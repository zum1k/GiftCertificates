package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;

public interface CertificateRepository {
    long add(GiftCertificate giftCertificate);

    void remove(long id);

    void update(long certificateId, GiftCertificate giftCertificate);

    List<GiftCertificate> findByPartName(String partName);

    List<GiftCertificate> findAll();

    List<GiftCertificate> sortByNameASC();

    List<GiftCertificate> findByTagName(String tagName);

    List<GiftCertificate> findById(long id);
}
