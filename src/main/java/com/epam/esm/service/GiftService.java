package com.epam.esm.service;

import com.epam.esm.entity.DateSortType;
import com.epam.esm.entity.dto.GiftCertificateDto;

import java.util.List;

public interface GiftService {
    GiftCertificateDto add(GiftCertificateDto giftCertificateDto);

    GiftCertificateDto remove(long id);

    GiftCertificateDto update(long certificateId, GiftCertificateDto giftCertificateDto);

    List<GiftCertificateDto> findAll(String tagName, String partName, String partDescription, DateSortType type);

    GiftCertificateDto findById(long id);

}
