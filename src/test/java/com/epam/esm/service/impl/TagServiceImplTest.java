package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.CriteriaSpecification;
import com.epam.esm.repository.specification.TagsByCertificateIdCriteriaSpecifications;
import com.epam.esm.repository.tag.TagRepository;
import com.epam.esm.service.mapper.tag.TagMapper;
import com.epam.esm.service.tag.TagServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import springfox.documentation.service.Tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
  @InjectMocks private TagServiceImpl service;
  @Mock private TagRepository repository;
  @Mock private TagMapper tagMapper;

  @Test
  void addTagIfNotExist_ShouldReturn_True_Test() {
    Tag tag = new Tag();
    tag.setName("name");
    TagDto tagDto = new TagDto("name");
    Mockito.when(repository.add(Mockito.any(Tag.class))).thenReturn(Optional.of(tag));
    Mockito.when(tagMapper.toDto(Mockito.any(Tag.class))).thenReturn(tagDto);
    Mockito.when(tagMapper.toEntity(Mockito.any(TagDto.class))).thenReturn(tag);

    TagDto resultDto = service.addTagIfNotExist(tagDto);

    Assertions.assertEquals(tagDto, resultDto);
  }

  @Test
  void addTagIfNotExist_ShouldReturn_Exception_Test() throws EntityNotAddedException {
    Tag tag = new Tag();
    tag.setName("name");
    TagDto tagDto = new TagDto("name1");
    Throwable expectedThrown = new EntityNotAddedException("Not added");

    Mockito.when(tagMapper.toEntity(Mockito.any(TagDto.class))).thenReturn(tag);
    Mockito.when(repository.add(Mockito.any(Tag.class))).thenThrow(expectedThrown);

    Assertions.assertThrows(
        EntityNotAddedException.class,
        () -> {
          service.addTagIfNotExist(tagDto);
        });
    Mockito.verify(repository).add(Mockito.eq(tag));
  }

  @Test
  void remove_ShouldReturn_True_Test() {
    Tag tag = new Tag();
    tag.setName("name");
    TagDto tagDto = new TagDto("name");
    Mockito.when(repository.remove(1)).thenReturn(Optional.of(tag));
    Mockito.when(tagMapper.toDto(Mockito.any(Tag.class))).thenReturn(tagDto);
    TagDto resultDto = service.remove(1);

    Assertions.assertEquals(tagDto, resultDto);
  }

  @Test
  void remove_ShouldReturn_Exception_Test() throws EntityNotDeletedException {
    long expectedTagId = 1L;
    Throwable expectedThrown = new EntityNotDeletedException("Not deleted", expectedTagId);
    Mockito.when(repository.remove(expectedTagId)).thenThrow(expectedThrown);
    Assertions.assertThrows(
        EntityNotDeletedException.class,
        () -> {
          service.remove(expectedTagId);
        });
    Mockito.verify(repository).remove(Mockito.eq(expectedTagId));
  }

  @Test
  void findOne_ShouldReturnTrue_Test() {
    Tag tag = new Tag();
    tag.setName("name");
    TagDto tagDto = new TagDto("name");

    Mockito.when(repository.findById(1)).thenReturn(tag);
    Mockito.when(tagMapper.toDto(Mockito.any(Tag.class))).thenReturn(tagDto);
    TagDto resultTag = service.findOne(1);
    Assertions.assertEquals(tagDto, resultTag);
  }

  @Test
  void findOne_ShouldReturnException_Test() throws EntityNotFoundException {
    long expectedTagId = 1L;
    Throwable expectedThrown = new EntityNotFoundException("Not deleted", expectedTagId);
    Mockito.when(repository.findById(expectedTagId)).thenThrow(expectedThrown);
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> {
          service.findOne(expectedTagId);
        });
    Mockito.verify(repository).findById(Mockito.eq(expectedTagId));
  }

  @Test
  void findAll_ShouldReturnTagDto_True_Test() {
    int page = 1;
    int pageSize = 3;
    RequestParametersDto parametersDto = new RequestParametersDto();
    parametersDto.setPage(page);
    parametersDto.setPageLimit(pageSize);
    TagDto tagDto = new TagDto("name");
    Tag tag = new Tag();
    tag.setName("name");
    List<Tag> allTags = Collections.singletonList(tag);
    List<TagDto> tagDtos = Collections.singletonList(tagDto);
    List<TagDto> expectedTags = Collections.singletonList(tagDto);

    Mockito.when(repository.findAll(page, pageSize)).thenReturn(allTags);
    Mockito.when(tagMapper.toDtoList(allTags)).thenReturn(tagDtos);
    List<TagDto> resultTagList = service.findAll(parametersDto);

    Assertions.assertEquals(expectedTags, resultTagList);
  }

  //  @Test
  //  void findByName_ShouldReturnTag_True_Test() {
  //    String expectedName = "name1";
  //    Tag tag = new Tag();
  //    tag.setName("name1");
  //    TagDto tagDto = new TagDto("name1");
  //    CriteriaSpecification<Tag> specification = new
  // TagsByNameCriteriaSpecifications(expectedName);
  //
  //    Mockito.when(repository.findTagByName(specification)).thenReturn(Optional.of(tag));
  //    Mockito.when(tagMapper.toDto(tag)).thenReturn(tagDto);
  //    String actualName = service.findByName(tagDto).get().getName();
  //    Assertions.assertEquals(expectedName, actualName);
  //  }

  //  @Test
  //  void findByName_ShouldReturnEmpty_True_Test() {
  //    String expectedName = "name1";
  //    Tag tag = new Tag();
  //    tag.setName("name1");
  //    TagDto tagDto = new TagDto("name1");
  //    CriteriaSpecification<Tag> specification = new
  // TagsByNameCriteriaSpecifications(expectedName);
  //    Mockito.when(repository.findTagByName(specification)).thenReturn(Optional.empty());
  //    Mockito.when(tagMapper.toDto(tag)).thenReturn(tagDto);
  //    Optional<TagDto> tagOptional = service.findByName(tagDto);
  //    Assertions.assertNotNull(tagOptional);
  //  }

//  @Test
//  void findAllByCertificateId_ShouldReturnTags_True_Test() {
//    long expectedCertificateId = 1;
//    TagDto expectedTagDto = new TagDto("name1");
//    List<Tag> allTags = new ArrayList<>();
//    List<TagDto> allDtos = new ArrayList<>();
//
//    for (int i = 1; i < 4; i++) {
//      String name = "name" + i;
//      Tag tag = new Tag();
//      TagDto tagDto = new TagDto(name);
//      tag.setName(name);
//      allTags.add(tag);
//      allDtos.add(tagDto);
//    }
//    CriteriaSpecification<Tag> specification  = new TagsByCertificateIdCriteriaSpecifications(expectedCertificateId);
//    Mockito.when(tagMapper.toDtoList(allTags)).thenReturn(allDtos);
//    Mockito.when(repository.findTagsByCertificateId(specification)).thenReturn(allTags);
//
//    TagDto actualTagDto = service.findAllByCertificateId(expectedCertificateId).get(0);
//    Assertions.assertEquals(expectedTagDto, actualTagDto);
//  }
}
