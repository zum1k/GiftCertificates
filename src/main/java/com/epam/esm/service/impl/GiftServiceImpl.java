package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.GiftService;
import com.epam.esm.service.mapper.certificate.CertificateMapper;
import com.epam.esm.service.mapper.tag.TagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GiftServiceImpl implements GiftService {
    private final CertificateMapper certificateMapper;
    private final TagMapper tagMapper;
    private final CertificateRepository certificateRepository;
    private final TagRepository tagRepository;
    private final GiftCertificateRepository giftCertificateRepository;

    @Override
    public long add(GiftCertificateDto giftCertificateDto) {
        log.info("add certificate");
        giftCertificateDto.setCreateDate(getCurrentTime());
        giftCertificateDto.setLastUpdateDate(getCurrentTime());
        long certificateId = certificateRepository.add(certificateMapper.toEntity(giftCertificateDto));
        for (TagDto tagDto : giftCertificateDto.getTags()) {
            if (tagRepository.findByName(tagDto.getName()).isEmpty()) {
                long tagId = tagRepository.add(tagMapper.toEntity(tagDto));
                giftCertificateRepository.add(certificateId, tagId);
            }
        }
        return certificateId;
    }

    @Override
    public void remove(long id) {
        log.info("remove certificate by id {}", id);
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
        return dtosMapper(certificateRepository.findByTagName(tagName));
    }

    @Override
    public List<GiftCertificateDto> findByPartName(String partName) {
        log.info("find by part name {}", partName);
        return dtosMapper(certificateRepository.findByPartName(partName));
    }

    @Override
    public List<GiftCertificateDto> sortByNameASC() {
        log.info("sort by name ASC");
        return dtosMapper(certificateRepository.sortByNameASC());
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        log.info("find all");
        return dtosMapper(certificateRepository.findAll());
    }

    @Override
    public GiftCertificateDto findById(long id) {
       if(dtosMapper(certificateRepository.findById(id)).isEmpty()){

       };
    }

    private List<GiftCertificateDto> dtosMapper(List<GiftCertificate> certificates) {
        List<GiftCertificateDto> dtos = new ArrayList<>();
        for (GiftCertificate certificate : certificates) {
            List<Tag> tags = tagRepository.findTagsByCertificateId(certificate.getCertificateId());
            dtos.add(certificateMapper.toDto(certificate, tagMapper.toDtoList(tags)));
        }
        return dtos;
    }

    private OffsetDateTime getCurrentTime() {
        return OffsetDateTime.now();
    }

    private long addGiftCertificateTag(long giftKey, long tagKey) {
        return giftCertificateRepository.add(giftKey, tagKey);
    }
}
