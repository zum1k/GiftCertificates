package com.epam.esm.service;

import com.epam.esm.entity.DateSortType;
import com.epam.esm.entity.dto.GiftCertificateDto;

import java.util.List;

public interface GiftService {
    public long add(GiftCertificateDto giftCertificateDto);

    public void remove(long id);

    public void update(long certificateId, GiftCertificateDto giftCertificateDto);

    public List<GiftCertificateDto> findAll(String tagName, String partName, String partDescription, DateSortType type);

    public GiftCertificateDto findById(long id);
}
