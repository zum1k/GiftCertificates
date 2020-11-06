package com.epam.esm.service.mapper.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.TagDto;

import java.util.List;

public interface TagMapper {
    Tag toEntity(TagDto dto);

    TagDto toDto(Tag tag);

    List<TagDto> toDtoList(List<Tag> tags);

}
