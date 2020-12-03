package com.epam.esm.service.impl;

import com.epam.esm.entity.DateSortType;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.certificate.CertificateRepository;
import com.epam.esm.repository.specifications.SpecificationCreator;
import com.epam.esm.service.GiftService;
import com.epam.esm.service.mapper.certificate.CertificateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GiftServiceImpl implements GiftService {
    @SuppressWarnings("all")
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GiftServiceImpl.class);
    private static final String CERTIFICATE = "Certificate";
    private final CertificateMapper certificateMapper;
    private final CertificateRepository certificateRepository;
    private final TagServiceImpl tagService;
    private final GiftCertificateTagService giftCertificateTagService;
    private final SpecificationCreator specificationCreator;

    @Override
    public GiftCertificateDto add(GiftCertificateDto giftCertificateDto) {
        log.info("add certificate");
        giftCertificateDto.setCreateDate(ZonedDateTime.now().withFixedOffsetZone());
        giftCertificateDto.setLastUpdateDate(ZonedDateTime.now().withFixedOffsetZone());
        Optional<GiftCertificate> certificate = certificateRepository.add(certificateMapper.toEntity(giftCertificateDto));
        if (certificate.isPresent()) {
            for (TagDto tagDto : giftCertificateDto.getTags()) {
                long tagId = tagService.addTagIfNotExist(tagDto).getId();
                giftCertificateTagService.add(certificate.get().getId(), tagId);
            }
            return certificateMapper.toDto(certificate.get(), giftCertificateDto.getTags());
        }
        throw new EntityNotAddedException(CERTIFICATE);
    }

    @Override
    public GiftCertificateDto remove(long id) {
        log.info("remove certificate by id {}", id);
        Optional<GiftCertificate> certificateDto = certificateRepository.remove(id);
        List<TagDto> tagDtos = tagService.findAllByCertificateId(certificateDto.get().getId());
        return certificateMapper.toDto(certificateDto.get(), tagDtos);
    }

    @Override
    public GiftCertificateDto update(long certificateId, GiftCertificateDto giftCertificateDto) {
        log.info("update certificate");
        GiftCertificate giftCertificate = certificateMapper.toEntity(giftCertificateDto);
        giftCertificate.setId(certificateId);
        giftCertificate.setLastUpdateDate(ZonedDateTime.now().withFixedOffsetZone());
        GiftCertificate certificate = certificateRepository.update(giftCertificate).get();
        for (TagDto tagDto : giftCertificateDto.getTags()) {
            Optional<TagDto> optionalTagDto = tagService.findByName(tagDto);
            if (optionalTagDto.isEmpty()) {
                long tagId = tagService.addTagIfNotExist(tagDto).getId();
                giftCertificateTagService.add(certificate.getId(), tagId);
            }
        }
        List<TagDto> tagDtos = tagService.findAllByCertificateId(certificateId);
        return certificateMapper.toDto(certificate, tagDtos);
    }

    @Override
    public List<GiftCertificateDto> findAll(String tagName, String partName, String partDescription, DateSortType type) {
        Optional<Specification> optionalSpecification = specificationCreator.receiveSpecification(tagName, partName, partDescription, type);
        if (optionalSpecification.isEmpty()) {
            return toDtos(certificateRepository.findAll());
        }
        return null; // toDtos(certificateRepository.findAllBySpecification(optionalSpecification.get()));
    }

    @Override
    public GiftCertificateDto findById(long id) {
        log.info("find by id {}", id);
        Optional<GiftCertificate> giftCertificateOptional = certificateRepository.findById(id);
        if (giftCertificateOptional.isEmpty()) {
            throw new EntityNotFoundException(CERTIFICATE);
        }
        List<TagDto> tagDtos = tagService.findAllByCertificateId(id);
        return certificateMapper.toDto(giftCertificateOptional.get(), tagDtos);
    }

    List<GiftCertificateDto> toDtos(List<GiftCertificate> certificates) {
        List<GiftCertificateDto> dtos = new ArrayList<>();
        for (GiftCertificate certificate : certificates) {
            List<TagDto> tags = tagService.findAllByCertificateId(certificate.getId());
            dtos.add(certificateMapper.toDto(certificate, tags));
        }
        return dtos;
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @Autowired
    @SuppressWarnings("all")
    public GiftServiceImpl(final CertificateMapper certificateMapper, final CertificateRepository certificateRepository, final TagServiceImpl tagService, final GiftCertificateTagService giftCertificateTagService, final SpecificationCreator specificationCreator) {
        this.certificateMapper = certificateMapper;
        this.certificateRepository = certificateRepository;
        this.tagService = tagService;
        this.giftCertificateTagService = giftCertificateTagService;
        this.specificationCreator = specificationCreator;
    }
    //</editor-fold>
}
