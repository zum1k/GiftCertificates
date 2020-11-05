package com.epam.esm.service.mapper.certificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CertificateMapperImpl implements CertificateMapper {
    @Override
    public  GiftCertificate toEntity(GiftCertificateDto dto) {
        return new GiftCertificate(dto.getName(), dto.getDescription(), dto.getPrice(),
                dto.getCreateDate(), dto.getLastUpdateDate(),dto.getDuration());
    }

    @Override
    public  GiftCertificateDto toDto(GiftCertificate certificate, List<TagDto> tags) {
        GiftCertificateDto dto = new GiftCertificateDto(certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getDuration(), tags);
        dto.setGiftId(certificate.getCertificateId());
        return dto;
    }
}
