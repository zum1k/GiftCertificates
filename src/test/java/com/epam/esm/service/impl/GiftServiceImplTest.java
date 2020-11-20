package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.repository.certificate.CertificateRepositoryImpl;
import com.epam.esm.service.mapper.certificate.CertificateMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GiftServiceImplTest {
    @InjectMocks
    private GiftServiceImpl service;
    @Mock
    private CertificateRepositoryImpl repository;
    @Mock
    private CertificateMapperImpl mapper;

    @Test
    void addCertificate_ShouldReturn_True_test() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("name1");
        certificate.setDescription("description1");
        certificate.setPrice(new BigDecimal("100.01"));
        certificate.setCreateDate(LocalDateTime.now().toString());
        certificate.setLastUpdateDate(LocalDateTime.now().toString());
        certificate.setDuration(10);

        TagDto tagDto = new TagDto("name");
        List<TagDto> tagDtos = Collections.singletonList(tagDto);

        GiftCertificateDto certificateDto = new GiftCertificateDto();
        certificateDto.setName("name1");
        certificateDto.setDescription("description1");
        certificateDto.setPrice(new BigDecimal("100.01"));
        certificateDto.setCreateDate(LocalDateTime.now().toString());
        certificateDto.setLastUpdateDate(LocalDateTime.now().toString());
        certificateDto.setDuration(10);
        certificateDto.setTags(tagDtos);

        Mockito.when(repository.add(Mockito.any(GiftCertificate.class))).thenReturn(Optional.of(certificate));
        Mockito.when(mapper.toDto(Mockito.any(GiftCertificate.class), Mockito.anyList())).thenReturn(certificateDto);

        GiftCertificateDto resultDto = service.add(certificateDto);

        Assertions.assertEquals(certificateDto, resultDto);

    }

    @Test()
    void shouldNotAddRowException() throws EntityNotAddedException {
    }

}