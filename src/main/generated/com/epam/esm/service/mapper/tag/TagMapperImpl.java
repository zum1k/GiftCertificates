package com.epam.esm.service.mapper.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.TagDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-06T01:45:48+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag toEntity(TagDto dto) {
        if ( dto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setTagId( dto.getId() );
        tag.setName( dto.getName() );
        tag.setCreateDate( dto.getCreateDate() );
        tag.setLastUpdateDate( dto.getLastUpdateDate() );

        return tag;
    }

    @Override
    public TagDto toDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setId( (int) tag.getTagId() );
        tagDto.setName( tag.getName() );
        tagDto.setCreateDate( tag.getCreateDate() );
        tagDto.setLastUpdateDate( tag.getLastUpdateDate() );

        return tagDto;
    }

    @Override
    public List<TagDto> toDtoList(List<Tag> tags) {
        if ( tags == null ) {
            return null;
        }

        List<TagDto> list = new ArrayList<TagDto>( tags.size() );
        for ( Tag tag : tags ) {
            list.add( toDto( tag ) );
        }

        return list;
    }
}
