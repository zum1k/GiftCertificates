package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.repository.GiftRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
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
    public long add(GiftCertificate giftCertificate) {
        return giftRepository.add(giftCertificate);
    }

    @Override
    public void remove(long id) {
        giftRepository.remove(id);
    }

    @Override
    public void update(GiftCertificate giftCertificate) {
        giftRepository.update(giftCertificate);
    }

    @Override
    public List<GiftCertificate> findByTagName(String tagName) {
        return null;
    }

    @Override
    public List<GiftCertificate> findByPartName(String partName) {
        return giftRepository.findByPartName(partName);
    }

    @Override
    public List<GiftCertificate> findByDescriptionPart(String descriptionPart) {
        return giftRepository.findByDescriptionPart(descriptionPart);
    }

    @Override
    public List<GiftCertificate> sortByNameASC(Comparator<GiftCertificate> comparator) {
        return null;
    }

    @Override
    public List<GiftCertificate> sortByNameDESC(Comparator<GiftCertificate> comparator) {
        return null;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return giftRepository.findAll();
    }

    private List<GiftCertificateDto> dtoMapper(List<GiftCertificate> giftCertificates){
        return null;
    }
}
