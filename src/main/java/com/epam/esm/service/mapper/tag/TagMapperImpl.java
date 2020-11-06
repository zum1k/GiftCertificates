package com.epam.esm.service.mapper.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.TagDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapperImpl implements TagMapper {
    @Override
    public Tag toEntity(TagDto dto) {
        return new Tag(dto.getName());
    }

    @Override
    public TagDto toDto(Tag tag) {
        TagDto tagDto = new TagDto(tag.getName());
        tagDto.setId(tag.getTagId());
        return tagDto;
    }

    @Override
    public List<TagDto> toDtoList(List<Tag> tags) {
        List<TagDto> dtos = new ArrayList<>();
        for (Tag tag : tags) {
            dtos.add(toDto(tag));
        }
        return dtos;
    }
}
