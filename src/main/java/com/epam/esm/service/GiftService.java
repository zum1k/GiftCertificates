package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;

import java.util.Comparator;
import java.util.List;

public interface GiftService {
    public long add(GiftCertificate giftCertificate);

    public void remove(long id);

    public void update(GiftCertificate giftCertificate);

    public List<GiftCertificate> findByTagName(String tagName);

    public List<GiftCertificate> findByPartName(String partName);

    public List<GiftCertificate> findByDescriptionPart(String descriptionPart);

    public List<GiftCertificate> sortByNameASC(Comparator<GiftCertificate> comparator);

    public List<GiftCertificate> sortByNameDESC(Comparator<GiftCertificate> comparator);

}
