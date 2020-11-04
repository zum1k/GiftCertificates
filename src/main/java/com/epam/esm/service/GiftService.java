package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;

import java.util.Comparator;
import java.util.List;

public interface GiftService {
    public long add(GiftCertificateDto giftCertificateDto);

    public void remove(long id);

    public void update(GiftCertificateDto giftCertificateDto);

    public List<GiftCertificateDto> findByTagName(String tagName);

    public List<GiftCertificate> findByPartName(String partName);

    public List<GiftCertificate> sortByNameASC(Comparator<GiftCertificate> comparator);

    public List<GiftCertificateDto> findAll();

    public GiftCertificateDto findById(long id);

}
