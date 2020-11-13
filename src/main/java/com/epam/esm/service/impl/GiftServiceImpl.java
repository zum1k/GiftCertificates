package com.epam.esm.service.impl;

import com.epam.esm.entity.DateSortType;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.Specification;
import com.epam.esm.repository.certificate.*;
import com.epam.esm.service.GiftCertificateTagService;
import com.epam.esm.service.GiftService;
import com.epam.esm.service.mapper.certificate.CertificateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GiftServiceImpl implements GiftService {
    private final String CERTIFICATE = "Certificate";

    private final CertificateMapper certificateMapper;
    private final CertificateRepository certificateRepository;
    private final TagServiceImpl tagService;
    private final GiftCertificateTagService giftCertificateTagService;


    @Override
    public GiftCertificateDto add(GiftCertificateDto giftCertificateDto) {
        log.info("add certificate");
        giftCertificateDto.setCreateDate(getCurrentTime());
        giftCertificateDto.setLastUpdateDate(getCurrentTime());
        Optional<GiftCertificate> certificate = certificateRepository.add(certificateMapper.toEntity(giftCertificateDto));
        if (certificate.isPresent()) {
            for (TagDto tagDto : giftCertificateDto.getTags()) {
                long tagId = tagService.addTagIfNotExist(tagDto).getId();
                giftCertificateTagService.add(certificate.get().getCertificateId(), tagId);
            }
        }
        return certificate.get();
    }

    @Override
    public GiftCertificateDto remove(long id) {
        log.info("remove certificate by id {}", id);
        Optional<GiftCertificate> certificateDto = certificateRepository.remove(id);
        List<TagDto> tagDtos = tagService.findAllByCertificateId(certificateDto.get().getCertificateId());
        return certificateMapper.toDto(certificateRepository.remove(id).get(), tagDtos);
    }

    @Override
    public GiftCertificateDto update(long certificateId, GiftCertificateDto giftCertificateDto) {
        log.info("update certificate");
        GiftCertificate giftCertificate = certificateMapper.toEntity(giftCertificateDto);
        giftCertificate.setCertificateId(certificateId);
        GiftCertificate certificate = certificateRepository.update(giftCertificate).get();
        List<TagDto> tagDtos = tagService.findAllByCertificateId(certificateId);
        return certificateMapper.toDto(certificate, tagDtos);
    }

    @Override
    public List<GiftCertificateDto> findAll(String tagName, String partName, String partDescription, DateSortType type) {
        List<Specification> specifications = new ArrayList<>();
        if (tagName != null) {
            Specification specification = new CertificatesByNameSpecification(tagName);
            specifications.add(specification);
        }
        if (partName != null) {
            Specification specification = new CertificatesByPartNameSpecification(partName);
            specifications.add(specification);
        }
        if (partDescription != null) {
            Specification specification = new CertificatesByPartDescriptionSpecification(partDescription);
            specifications.add(specification);
        }
        if (type != null) {
            Specification specification = new CertificatesByDateSpecification(type);
            specifications.add(specification);
        }
        if (specifications.isEmpty()) {
            return dtosMapper(certificateRepository.findAll());
        }
        return dtosMapper(certificateRepository.findAllBySpecification(new SpecificationBuilder(specifications)));
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

    private List<GiftCertificateDto> dtosMapper(List<GiftCertificate> certificates) {
        log.info("mapping dtos");
        List<GiftCertificateDto> dtos = new ArrayList<>();
        for (GiftCertificate certificate : certificates) {
            List<TagDto> tags = tagService.findAllByCertificateId(certificate.getCertificateId());
            dtos.add(certificateMapper.toDto(certificate, tags));
        }
        return dtos;
    }

    private LocalDate getCurrentTime() {
        return LocalDate.now();
    }

}
