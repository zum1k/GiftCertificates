package com.epam.esm.service.mapper.certificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;

public interface CertificateMapper {
    GiftCertificate toEntity(GiftCertificateDto dto);

    GiftCertificateDto toDto(GiftCertificate certificate);
}
