package com.epam.esm.service.mapper.certificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.service.mapper.tag.TagMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TagMapper.class})
public interface CertificateMapper {

    GiftCertificate toEntity(GiftCertificateDto dto);

    GiftCertificateDto toDto(GiftCertificate certificate, List<TagDto> tags);

    List<GiftCertificateDto> toDtos(List<GiftCertificate> certificates);
}
