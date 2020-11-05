package com.epam.esm.service.mapper.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.TagDto;

public interface TagMapper {
    Tag toEntity(TagDto dto);

    TagDto toDto(Tag tag);

}
