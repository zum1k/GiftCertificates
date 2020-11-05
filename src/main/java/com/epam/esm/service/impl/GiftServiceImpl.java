package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.GiftRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.GiftService;
import com.epam.esm.service.mapper.certificate.CertificateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GiftServiceImpl implements GiftService {
    private final CertificateMapper certificateMapper;
    private final GiftRepository certificateRepository;
    private final TagRepository tagRepository;


    @Autowired
    public GiftServiceImpl(GiftRepository giftRepository, TagRepository tagRepository,
                           CertificateMapper mapper) {
        this.certificateRepository = giftRepository;
        this.tagRepository = tagRepository;
        this.certificateMapper = mapper;

    }

    @Override
    public long add(GiftCertificateDto giftCertificateDto) {
        log.info("add certificate");
        giftCertificateDto.setCreateDate(getCurrentTime());
        giftCertificateDto.setLastUpdateDate(getCurrentTime());
        return certificateRepository.add(certificateMapper.toEntity(giftCertificateDto));
    }

    @Override
    public void remove(long id) {
        log.info("remove certificate");
        certificateRepository.remove(id);
    }

    @Override
    public void update(GiftCertificateDto giftCertificateDto) {
        log.info("update certificate");
        certificateRepository.update(certificateMapper.toEntity(giftCertificateDto));
    }

    @Override
    public List<GiftCertificateDto> findByTagName(String tagName) {
        log.info("find by tag name {}", tagName);
        return null;
    }

    @Override
    public List<GiftCertificateDto> findByPartName(String partName) {
        log.info("find by part name {}", partName);
        return certificateRepository.findByPartName(partName);
    }

    @Override
    public List<GiftCertificateDto> sortByNameASC() {
        log.info("sort by name ASC");
        return null;
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        log.info("find all");
        return dtosMapper(certificateRepository.findAll());
    }

    @Override
    public GiftCertificateDto findById(long id) {
        return null;
    }

    private List<GiftCertificateDto> dtosMapper(List<GiftCertificate> certificates) {
        List<GiftCertificateDto> dtos = new ArrayList<>();
        for (GiftCertificate certificate : certificates) {
            certificate.getCertificateId();
            dtos.add(certificateMapper.toDto(certificate).setTags();
        }
        return dtos;
    }

    private OffsetDateTime getCurrentTime() {
        return OffsetDateTime.now();
    }
}
