package com.epam.esm.service.mapper.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.TagDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mappings({
            @Mapping(target = "tagId", source = "dto.id"),
            @Mapping(target = "certificate", ignore = true)
    })
    Tag toEntity(TagDto dto);

    @Mapping(target = "id", source = "tag.tagId")
    TagDto toDto(Tag tag);

    List<TagDto> toDtoList(List<Tag> tags);

}
