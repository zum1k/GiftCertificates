package com.epam.esm.service.mapper.certificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.service.mapper.tag.TagMapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-06T01:45:48+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class CertificateMapperImpl implements CertificateMapper {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public GiftCertificate toEntity(GiftCertificateDto dto) {
        if ( dto == null ) {
            return null;
        }

        GiftCertificate giftCertificate = new GiftCertificate();

        giftCertificate.setGiftId( dto.getGiftId() );
        giftCertificate.setName( dto.getName() );
        giftCertificate.setDescription( dto.getDescription() );
        giftCertificate.setPrice( dto.getPrice() );
        giftCertificate.setCreateDate( dto.getCreateDate() );
        giftCertificate.setLastUpdateDate( dto.getLastUpdateDate() );
        giftCertificate.setDuration( dto.getDuration() );
        giftCertificate.setTags( tagDtoListToTagSet( dto.getTags() ) );

        return giftCertificate;
    }

    @Override
    public GiftCertificateDto toDto(GiftCertificate certificate) {
        if ( certificate == null ) {
            return null;
        }

        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();

        giftCertificateDto.setGiftId( certificate.getGiftId() );
        giftCertificateDto.setName( certificate.getName() );
        giftCertificateDto.setDescription( certificate.getDescription() );
        giftCertificateDto.setPrice( certificate.getPrice() );
        giftCertificateDto.setCreateDate( certificate.getCreateDate() );
        giftCertificateDto.setLastUpdateDate( certificate.getLastUpdateDate() );
        giftCertificateDto.setDuration( certificate.getDuration() );
        giftCertificateDto.setTags( tagSetToTagDtoList( certificate.getTags() ) );

        return giftCertificateDto;
    }

    @Override
    public List<GiftCertificateDto> toDtos(List<GiftCertificate> certificates) {
        if ( certificates == null ) {
            return null;
        }

        List<GiftCertificateDto> list = new ArrayList<GiftCertificateDto>( certificates.size() );
        for ( GiftCertificate giftCertificate : certificates ) {
            list.add( toDto( giftCertificate ) );
        }

        return list;
    }

    protected Set<Tag> tagDtoListToTagSet(List<TagDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Tag> set = new HashSet<Tag>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( TagDto tagDto : list ) {
            set.add( tagMapper.toEntity( tagDto ) );
        }

        return set;
    }

    protected List<TagDto> tagSetToTagDtoList(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        List<TagDto> list = new ArrayList<TagDto>( set.size() );
        for ( Tag tag : set ) {
            list.add( tagMapper.toDto( tag ) );
        }

        return list;
    }
}
