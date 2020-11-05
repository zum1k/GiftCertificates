package com.epam.esm.service.mapper.certificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;

public class CertificateMapperImpl implements CertificateMapper {
    @Override
    public GiftCertificate toEntity(GiftCertificateDto dto) {
        return new GiftCertificate(dto.getName(), dto.getDescription(), dto.getPrice(),
                dto.getCreateDate(), dto.getLastUpdateDate(), dto.getDuration());
    }

    @Override
    public GiftCertificateDto toDto(GiftCertificate certificate) {
        GiftCertificateDto dto = new GiftCertificateDto(certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getCreateDate(), certificate.getLastUpdateDate(),
                certificate.getDuration());
        dto.setGiftId(certificate.getID());
        return dto;
    }
}
