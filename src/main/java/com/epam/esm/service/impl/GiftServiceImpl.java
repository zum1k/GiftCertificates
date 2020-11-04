package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.repository.GiftRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GiftServiceImpl implements GiftService {
    private final TagRepository tagRepository;
    private final GiftRepository giftRepository;

    @Autowired
    public GiftServiceImpl(GiftRepository giftRepository, TagRepository tagRepository) {
        this.tagRepository = tagRepository;
        this.giftRepository = giftRepository;
    }

    @Override
    public long add(GiftCertificateDto giftCertificateDto) {
        return giftRepository.add(giftCertificate);
    }

    @Override
    public void remove(long id) {
        giftRepository.remove(id);
    }

    @Override
    public void update(GiftCertificateDto giftCertificateDto) {
        giftRepository.update(giftCertificate);
    }

    @Override
    public List<GiftCertificateDto> findByTagName(String tagName) {
        return null;
    }

    @Override
    public List<GiftCertificateDto> findByPartName(String partName) {
        return giftRepository.findByPartName(partName);
    }

    @Override
    public List<GiftCertificateDto> sortByNameASC() {
        return null;
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        return giftRepository.findAll();
    }

    @Override
    public GiftCertificateDto findById(long id) {
        return null;
    }

    private List<GiftCertificateDto> dtoMapper(List<GiftCertificate> giftCertificates) {
        for (GiftCertificate giftCertificate : giftCertificates) {
            long id = giftCertificate.getID();
            tagRepository.findOne(id);
        }
        return null;
    }

    private GiftCertificate certificateMapper(GiftCertificateDto dto) {
        return new GiftCertificate(dto.getName(), dto.getDescription(), dto.getPrice(),
                dto.getCreateDate(), dto.getLastUpdateDate(), dto.getDuration());
    }

    private GiftCertificateDto dtoMapper(GiftCertificate certificate, List<Tag> tags) {

        return new GiftCertificateDto(certificate.getID(), certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getCreateDate(), certificate.getLastUpdateDate(),
                certificate.getDuration(), tags)
    }

    private TagDto tagMapper(Tag tag) {
        return new TagDto();
    }
}
