package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.mapper.tag.TagMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void remove() {
        Tag tag = new Tag("name");
        TagDto tagDto = new TagDto("name");
        Mockito.when(repository.remove(1)).thenReturn(Optional.of(tag));
        Mockito.when(tagMapper.toDto(Mockito.any(Tag.class))).thenReturn(tagDto);
        TagDto resultDto = service.remove(1);

        Assertions.assertEquals(tagDto, resultDto);
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
    void findAll() {
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

}