package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;

public interface GiftRepository {
    public long add(GiftCertificate giftCertificate);

    public void remove(long id);

    public void update(GiftCertificate giftCertificate);

    public List<GiftCertificate> findByPartName(String partName);

    public List<GiftCertificate> findByDescriptionPart(String descriptionPart);

}
