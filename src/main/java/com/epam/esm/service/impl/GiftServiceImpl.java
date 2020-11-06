package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.EntityNotUpdatedException;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.GiftService;
import com.epam.esm.service.mapper.certificate.CertificateMapper;
import com.epam.esm.service.mapper.tag.TagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GiftServiceImpl implements GiftService {
    private final String CERTIFICATE = "Certificate";
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
                addGiftCertificateTag(certificateId, tagId);
            } else {
                addGiftCertificateTag(certificateId, tagDto.getId());
            }
        }
        return certificateId;
    }

    @Override
    public void remove(long id) {
        log.info("remove certificate by id {}", id);
        if (dtosMapper(certificateRepository.findById(id)).isEmpty()) {
            throw new EntityNotDeletedException(CERTIFICATE);
        }
        certificateRepository.remove(id);
    }

    @Override
    public void update(long certificateId, GiftCertificateDto giftCertificateDto) {
        log.info("update certificate");
        if (dtosMapper(certificateRepository.findById(certificateId)).isEmpty()) {
            throw new EntityNotUpdatedException(CERTIFICATE);
        }
        certificateRepository.update(certificateId, certificateMapper.toEntity(giftCertificateDto));
    }

    @Override
    public List<GiftCertificateDto> findByTagName(String tagName) {
        log.info("find by tag name {}", tagName);
        if (dtosMapper(certificateRepository.findByTagName(tagName)).isEmpty()) {
            throw new EntityNotFoundException(CERTIFICATE);
        }
        return dtosMapper(certificateRepository.findByTagName(tagName));
    }

    @Override
    public List<GiftCertificateDto> findByPartName(String partName) {
        log.info("find by part name {}", partName);
        if (dtosMapper(certificateRepository.findByPartName(partName)).isEmpty()) {
            throw new EntityNotFoundException(CERTIFICATE);
        }
        return dtosMapper(certificateRepository.findByPartName(partName));
    }

    @Override
    public List<GiftCertificateDto> sortByNameASC() {
        log.info("sort by name ASC");
        if (dtosMapper(certificateRepository.sortByNameASC()).isEmpty()) {
            throw new EntityNotFoundException(CERTIFICATE);
        }
        return dtosMapper(certificateRepository.sortByNameASC());
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        log.info("find all");
        if (dtosMapper(certificateRepository.findAll()).isEmpty()) {
            throw new EntityNotFoundException(CERTIFICATE);
        }
        return dtosMapper(certificateRepository.findAll());
    }

    @Override
    public GiftCertificateDto findById(long id) {
        if (dtosMapper(certificateRepository.findById(id)).isEmpty()) {
            throw new EntityNotFoundException(CERTIFICATE);
        }
        return dtosMapper(certificateRepository.findById(id)).get(0);
    }

    private List<GiftCertificateDto> dtosMapper(List<GiftCertificate> certificates) {
        List<GiftCertificateDto> dtos = new ArrayList<>();
        for (GiftCertificate certificate : certificates) {
            List<Tag> tags = tagRepository.findTagsByCertificateId(certificate.getCertificateId());
            dtos.add(certificateMapper.toDto(certificate, tagMapper.toDtoList(tags)));
        }
        return dtos;
    }

    private LocalDate getCurrentTime() {
        return LocalDate.now();
    }

    private long addGiftCertificateTag(long giftKey, long tagKey) {
        return giftCertificateRepository.add(giftKey, tagKey);
    }
}
