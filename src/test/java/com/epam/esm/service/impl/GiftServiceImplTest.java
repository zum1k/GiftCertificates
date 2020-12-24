package com.epam.esm.service.impl;

import com.epam.esm.entity.DateSortType;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.EntityNotUpdatedException;
import com.epam.esm.repository.certificate.CertificateRepositoryImpl;
import com.epam.esm.repository.specification.SpecificationCreator;
import com.epam.esm.service.certificate.GiftServiceImpl;
import com.epam.esm.service.mapper.certificate.CertificateMapper;
import com.epam.esm.service.tag.TagServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GiftServiceImplTest {
  @InjectMocks
  private GiftServiceImpl service;
  @Mock
  private CertificateRepositoryImpl repository;
  @Mock private CertificateMapper mapper;
  @Mock private TagServiceImpl tagService;
  @Mock private SpecificationCreator creator;

  @Test
  void addCertificate_ShouldReturnCertificate_True_test() {
    String expectedName = "name1";
    GiftCertificate certificate =
        new GiftCertificate(
            expectedName,
            "description1",
            new BigDecimal("100.10"),
            ZonedDateTime.now().withFixedOffsetZone(),
            ZonedDateTime.now().withFixedOffsetZone(),
            10);
    certificate.setGiftId(1L);
    long giftId = 1L;
    long tagId = 1L;

    TagDto tagDto = new TagDto("name1");
    tagDto.setId(1);
    List<TagDto> dtos = Collections.singletonList(tagDto);

    GiftCertificateDto certificateDto =
        new GiftCertificateDto("name1", "description1", new BigDecimal("100.10"), 10, dtos);

    Mockito.when(repository.add(certificate)).thenReturn(Optional.of(certificate));
    Mockito.when(mapper.toEntity(certificateDto)).thenReturn(certificate);
    Mockito.when(mapper.toDto(certificate, dtos)).thenReturn(certificateDto);
    Mockito.when(tagService.addTagIfNotExist(Mockito.any(TagDto.class))).thenReturn(tagDto);

    String actualName = service.add(certificateDto).getName();

    Assertions.assertEquals(certificate, actualName);
  }

  @Test()
  void addCertificate_ShouldReturnException_Test() throws EntityNotAddedException {
    String expectedName = "name1";
    GiftCertificate certificate =
        new GiftCertificate(
            expectedName,
            "description1",
            new BigDecimal("100.10"),
            ZonedDateTime.now().withFixedOffsetZone(),
            ZonedDateTime.now().withFixedOffsetZone(),
            10);
    certificate.setId(1L);
    TagDto tagDto = new TagDto("name1");
    tagDto.setId(1);
    List<TagDto> dtos = Collections.singletonList(tagDto);
    GiftCertificateDto certificateDto =
        new GiftCertificateDto("name1", "description1", new BigDecimal("100.10"), 10, dtos);
    Throwable expectedThrown = new EntityNotAddedException("Not added");

    Mockito.when(mapper.toEntity(Mockito.any(GiftCertificateDto.class))).thenReturn(certificate);
    Mockito.when(repository.add(Mockito.any(GiftCertificate.class))).thenThrow(expectedThrown);

    Assertions.assertThrows(
        EntityNotAddedException.class,
        () -> {
          service.add(certificateDto);
        });
    Mockito.verify(repository).add(Mockito.eq(certificate));
  }

  @Test
  void removeCertificate_ShouldReturnCertificateDto_True_Test() {
    long expectedCertificateID = 1L;
    GiftCertificate certificate =
        new GiftCertificate(
            "name1",
            "description1",
            new BigDecimal("100.10"),
            ZonedDateTime.now().withFixedOffsetZone(),
            ZonedDateTime.now().withFixedOffsetZone(),
            10);
    certificate.setId(expectedCertificateID);
    TagDto tagDto = new TagDto("name1");
    tagDto.setId(1);
    List<TagDto> dtos = Collections.singletonList(tagDto);
    GiftCertificateDto certificateDto =
        new GiftCertificateDto("name1", "description1", new BigDecimal("100.10"), 10, dtos);

    Mockito.when(repository.remove(expectedCertificateID)).thenReturn(Optional.of(certificate));
    Mockito.when(mapper.toDto(certificate, dtos)).thenReturn(certificateDto);
    Mockito.when(tagService.findAllByCertificateId(expectedCertificateID)).thenReturn(dtos);

    GiftCertificateDto actualTagDto = service.remove(expectedCertificateID);
    Assertions.assertEquals(certificateDto, actualTagDto);
    Mockito.verify(repository).remove(Mockito.eq(expectedCertificateID));
  }

  @Test
  void removeCertificate_ShouldReturnException_Test() throws EntityNotDeletedException {
    long expectedCertificateID = 1L;
    Throwable expectedThrown = new EntityNotDeletedException("Not deleted", expectedCertificateID);
    Mockito.when(repository.remove(expectedCertificateID)).thenThrow(expectedThrown);
    Assertions.assertThrows(
        EntityNotDeletedException.class,
        () -> {
          service.remove(expectedCertificateID);
        });
    Mockito.verify(repository).remove(Mockito.eq(expectedCertificateID));
  }

  @Test
  void updateCertificate_ShouldReturnCertificateDto_Test() {
    long expectedCertificateId = 1L;
    TagDto tagDto = new TagDto("name1");
    tagDto.setId(1);
    List<TagDto> tagDtos = Collections.singletonList(tagDto);
    GiftCertificateDto certificateDto =
        new GiftCertificateDto("name1", "description1", new BigDecimal("100.10"), 10, tagDtos);
    GiftCertificate certificate =
        new GiftCertificate(
            "name1",
            "description1",
            new BigDecimal("100.10"),
            ZonedDateTime.now().withFixedOffsetZone(),
            ZonedDateTime.now().withFixedOffsetZone(),
            10);
    certificate.setId(1L);

    Mockito.when(mapper.toEntity(Mockito.any(GiftCertificateDto.class))).thenReturn(certificate);
    Mockito.when(repository.update(Mockito.any(GiftCertificate.class)))
        .thenReturn(Optional.of(certificate));
    Mockito.when(tagService.findByName(Mockito.any(TagDto.class))).thenReturn(Optional.of(tagDto));
    Mockito.when(tagService.findAllByCertificateId(expectedCertificateId)).thenReturn(tagDtos);
    Mockito.when(mapper.toDto(certificate, tagDtos)).thenReturn(certificateDto);

    GiftCertificateDto actualDto = service.update(expectedCertificateId, certificateDto);
    Assertions.assertEquals(certificateDto, actualDto);
  }

  @Test
  void updateCertificate_ShouldReturnException_Test() throws EntityNotUpdatedException {
    long expectedCertificateId = 1L;
    Throwable expectedThrown = new EntityNotUpdatedException("Not updated", expectedCertificateId);
    TagDto tagDto = new TagDto("name1");
    tagDto.setId(1);
    List<TagDto> tagDtos = Collections.singletonList(tagDto);
    GiftCertificateDto certificateDto =
        new GiftCertificateDto("name1", "description1", new BigDecimal("100.10"), 10, tagDtos);
    GiftCertificate certificate =
        new GiftCertificate(
            "name1",
            "description1",
            new BigDecimal("100.10"),
            ZonedDateTime.now().withFixedOffsetZone(),
            ZonedDateTime.now().withFixedOffsetZone(),
            10);
    certificate.setId(1L);

    Mockito.when(mapper.toEntity(Mockito.any(GiftCertificateDto.class))).thenReturn(certificate);
    Mockito.when(repository.update(certificate)).thenThrow(expectedThrown);

    Assertions.assertThrows(
        EntityNotUpdatedException.class,
        () -> {
          service.update(expectedCertificateId, certificateDto);
        });
    Mockito.verify(repository).update(Mockito.eq(certificate));
  }

  @Test
  void toDtos_ShouldReturnCertificateDtoList_Test() {
    TagDto tagDto = new TagDto("name1");
    tagDto.setId(1);
    List<TagDto> tagDtos = Arrays.asList(tagDto);
    long expectedCertificateId = 1L;
    GiftCertificateDto certificateDto =
        new GiftCertificateDto("name1", "description1", new BigDecimal("100.10"), 10, tagDtos);
    certificateDto.setCreateDate(ZonedDateTime.now().withFixedOffsetZone());
    certificateDto.setLastUpdateDate(ZonedDateTime.now().withFixedOffsetZone());
    List<GiftCertificateDto> certificateDtoList = Arrays.asList(certificateDto);
    GiftCertificate certificate =
        new GiftCertificate(
            "name1",
            "description1",
            new BigDecimal("100.10"),
            ZonedDateTime.now().withFixedOffsetZone(),
            ZonedDateTime.now().withFixedOffsetZone(),
            10);
    certificate.setId(expectedCertificateId);
    List<GiftCertificate> certificateList = Arrays.asList(certificate);

    Mockito.when(tagService.findAllByCertificateId(expectedCertificateId)).thenReturn(tagDtos);
    Mockito.when(mapper.toDto(certificate, tagDtos)).thenReturn(certificateDto);
    List<GiftCertificateDto> actualList = service.toDtos(certificateList);
    Assertions.assertEquals(certificateDtoList.get(0), actualList.get(0));
  }

  @Test
  void findAll_ShouldReturnCertificates_Test() {
    String partName = "";
    String partDescription = "";
    String tagName = "";
    DateSortType type = DateSortType.ASC;
    TagDto tagDto = new TagDto("name1");
    tagDto.setId(1);

    long expectedCertificateId = 1L;
    List<TagDto> tagDtos = Arrays.asList(tagDto);
    GiftCertificateDto certificateDto =
        new GiftCertificateDto("name1", "description1", new BigDecimal("100.10"), 10, tagDtos);
    certificateDto.setCreateDate(ZonedDateTime.now().withFixedOffsetZone());
    certificateDto.setLastUpdateDate(ZonedDateTime.now().withFixedOffsetZone());
    List<GiftCertificateDto> certificateDtoList = Arrays.asList(certificateDto);
    GiftCertificate certificate =
        new GiftCertificate(
            "name1",
            "description1",
            new BigDecimal("100.10"),
            ZonedDateTime.now().withFixedOffsetZone(),
            ZonedDateTime.now().withFixedOffsetZone(),
            10);
    certificate.setId(expectedCertificateId);
    List<GiftCertificate> certificateList = Arrays.asList(certificate);

    Mockito.when(creator.createSpecifications(tagName, partName, partDescription, type))
        .thenReturn(Optional.empty());
    Mockito.when(repository.findAll()).thenReturn(certificateList);
    Mockito.when(tagService.findAllByCertificateId(expectedCertificateId)).thenReturn(tagDtos);
    Mockito.when(mapper.toDto(certificate, tagDtos)).thenReturn(certificateDto);

    List<GiftCertificateDto> actualList = service.findAll(tagName, partName, partDescription, type);
    Assertions.assertEquals(certificateDtoList, actualList);
  }

  @Test
  void findById_ShouldReturnCertificateDto_Test() {
    long expectedCertificateId = 1L;
    GiftCertificate certificate =
        new GiftCertificate(
            "name1",
            "description1",
            new BigDecimal("100.10"),
            ZonedDateTime.now().withFixedOffsetZone(),
            ZonedDateTime.now().withFixedOffsetZone(),
            10);
    certificate.setId(expectedCertificateId);
    TagDto tagDto = new TagDto("name1");
    tagDto.setId(1);
    List<TagDto> tagDtos = Collections.singletonList(tagDto);
    GiftCertificateDto giftCertificateDto =
        new GiftCertificateDto("name1", "description1", new BigDecimal("100.10"), 10, tagDtos);
    giftCertificateDto.setGiftId(expectedCertificateId);

    Mockito.when(repository.findById(expectedCertificateId)).thenReturn(Optional.of(certificate));
    Mockito.when(tagService.findAllByCertificateId(expectedCertificateId)).thenReturn(tagDtos);
    Mockito.when(mapper.toDto(certificate, tagDtos)).thenReturn(giftCertificateDto);
    GiftCertificateDto actualCertificateDto = service.findById(expectedCertificateId);

    Assertions.assertEquals(giftCertificateDto, actualCertificateDto);
  }

  @Test
  void findById_ShouldReturnException_Test() throws EntityNotFoundException {
    long expectedCertificateID = 1L;
    Mockito.when(repository.findById(expectedCertificateID)).thenReturn(Optional.empty());
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> {
          service.findById(expectedCertificateID);
        });
    Mockito.verify(repository).findById(Mockito.eq(expectedCertificateID));
  }
}
