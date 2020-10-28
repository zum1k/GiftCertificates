package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.GiftRepository;
import com.epam.esm.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {
    private final GiftRepository giftRepository;

    @Autowired
    public GiftServiceImpl(GiftRepository giftRepository) {
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
        return null;
    }

    @Override
    public List<GiftCertificate> findByDescriptionPart(String descriptionPart) {
        return null;
    }

    @Override
    public List<GiftCertificate> sortByNameASC(Comparator<GiftCertificate> comparator) {
        return null;
    }

    @Override
    public List<GiftCertificate> sortByNameDESC(Comparator<GiftCertificate> comparator) {
        return null;
    }
}
