package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.tag.TagRepository;
import com.epam.esm.service.mapper.tag.TagMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    @InjectMocks
    private TagServiceImpl service;
    @Mock
    private TagRepository repository;
    @Mock
    private TagMapper tagMapper;

    @Test
    void addTagIfNotExist_ShouldReturn_True_Test() {
        Tag tag = new Tag("name");
        TagDto tagDto = new TagDto("name");
        Mockito.when(repository.add(Mockito.any(Tag.class))).thenReturn(Optional.of(tag));
        Mockito.when(tagMapper.toDto(Mockito.any(Tag.class))).thenReturn(tagDto);
        Mockito.when(tagMapper.toEntity(Mockito.any(TagDto.class))).thenReturn(tag);

        TagDto resultDto = service.addTagIfNotExist(tagDto);

        Assertions.assertEquals(tagDto, resultDto);
    }

    @Test
    void addTagIfNotExist_ShouldReturn_Exception_Test() throws EntityNotAddedException {
        Tag tag = new Tag("name1");
        TagDto tagDto = new TagDto("name1");
        Throwable expectedThrown = new EntityNotAddedException("Not added");

        Mockito.when(tagMapper.toEntity(Mockito.any(TagDto.class))).thenReturn(tag);
        Mockito.when(repository.add(Mockito.any(Tag.class))).thenThrow(expectedThrown);

        Assertions.assertThrows(EntityNotAddedException.class, () -> {
            service.addTagIfNotExist(tagDto);
        });
        Mockito.verify(repository).add(Mockito.eq(tag));
    }

    @Test
    void remove_ShouldReturn_True_Test() {
        Tag tag = new Tag("name");
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
        Assertions.assertThrows(EntityNotDeletedException.class, () -> {
            service.remove(expectedTagId);
        });
        Mockito.verify(repository).remove(Mockito.eq(expectedTagId));
    }

    @Test
    void findOne_ShouldReturnTrue_Test() {
        Tag tag = new Tag("name");
        TagDto tagDto = new TagDto("name");

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(tag));
        Mockito.when(tagMapper.toDto(Mockito.any(Tag.class))).thenReturn(tagDto);
        TagDto resultTag = service.findOne(1);
        Assertions.assertEquals(tagDto, resultTag);
    }

    @Test
    void findOne_ShouldReturnException_Test() throws EntityNotFoundException {
        long expectedTagId = 1L;
        Throwable expectedThrown = new EntityNotFoundException("Not deleted", expectedTagId);
        Mockito.when(repository.findById(expectedTagId)).thenThrow(expectedThrown);
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            service.findOne(expectedTagId);
        });
        Mockito.verify(repository).findById(Mockito.eq(expectedTagId));
    }

    @Test
    void findAll_ShouldReturn_True_Test() {
        TagDto tagDto = new TagDto("name");
        Tag tag = new Tag("name");
        List<Tag> allTags = Collections.singletonList(tag);
        List<TagDto> tagDtos = Collections.singletonList(tagDto);
        List<TagDto> expectedTags = Collections.singletonList(tagDto);

        Mockito.when(repository.findAll()).thenReturn(allTags);
        Mockito.when(tagMapper.toDtoList(allTags)).thenReturn(tagDtos);
        List<TagDto> resultTagList = service.findAll();

        Assertions.assertEquals(expectedTags, resultTagList);
    }

    @Test
    void findByName_ShouldReturnTag_True_Test() {
        String expectedName = "name1";
        Tag tag = new Tag("name1");
        TagDto tagDto = new TagDto("name1");
        Mockito.when(repository.findByName(expectedName)).thenReturn(Optional.of(tag));
        Mockito.when(tagMapper.toDto(tag)).thenReturn(tagDto);
        String actualName = service.findByName(tagDto).get().getName();
        Assertions.assertEquals(expectedName, actualName);

    }

    @Test
    void findByName_ShouldReturnEmpty_True_Test() {
        String expectedName = "name1";
        TagDto tagDto = new TagDto("name1");
        Mockito.when(repository.findByName(expectedName)).thenReturn(Optional.empty());
        Optional<TagDto> tagOptional = service.findByName(tagDto);
        Assertions.assertNotNull(tagOptional);

    }

    @Test
    void findAllByCertificateId_ShouldReturnTags_True_Test() {
        long expectedCertificateId = 1;
        TagDto expectedTagDto = new TagDto("name1");
        Tag[] tags = new Tag[]{new Tag("name1"), new Tag("name2"), new Tag("name3")};
        TagDto[] tagDtos = new TagDto[]{new TagDto("name1"), new TagDto("name2"), new TagDto("name3")};
        List<Tag> allTags = Arrays.asList(tags);
        List<TagDto> allDtos = Arrays.asList(tagDtos);
        Mockito.when(repository.findTagsByCertificateId(expectedCertificateId)).thenReturn(allTags);
        Mockito.when(tagMapper.toDtoList(allTags)).thenReturn(allDtos);
        TagDto actualTagDto = service.findAllByCertificateId(expectedCertificateId).get(0);
        Assertions.assertEquals(expectedTagDto, actualTagDto);
    }
}