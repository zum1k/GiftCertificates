package com.epam.esm.service.mapper.certificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.service.mapper.tag.TagMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {TagMapper.class})
public interface CertificateMapper {
  @Mapping(target = "orders", ignore = true)
  GiftCertificate toEntity(GiftCertificateDto dto);

  GiftCertificateDto toDto(GiftCertificate certificate);

  List<GiftCertificateDto> toDtos(List<GiftCertificate> certificates);
}
