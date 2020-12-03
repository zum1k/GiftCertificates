package com.epam.esm.service.mapper.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mapping(target = "tagId", source = "id")
    Tag toEntity(TagDto dto);

    TagDto toDto(Tag tag);

    List<TagDto> toDtoList(List<Tag> tags);

}
