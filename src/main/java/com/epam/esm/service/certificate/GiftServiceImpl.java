package com.epam.esm.service.certificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.EntityNotUpdatedException;
import com.epam.esm.repository.CriteriaSpecification;
import com.epam.esm.repository.certificate.CertificateRepository;
import com.epam.esm.repository.specification.SpecificationCreator;
import com.epam.esm.service.tag.TagService;
import com.epam.esm.service.mapper.certificate.CertificateMapper;
import com.epam.esm.service.mapper.tag.TagMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GiftServiceImpl implements GiftService {
    private static final Logger log = LoggerFactory.getLogger(GiftServiceImpl.class);
    private static final String CERTIFICATE = "Certificate";
    private final CertificateMapper mapper;
    private final CertificateRepository repository;
    private final TagService tagService;
    private final SpecificationCreator creator;
    private final TagMapper tagMapper;


    @Transactional
    @Override
    public GiftCertificateDto add(GiftCertificateDto giftCertificateDto) {
        Set<Tag> tags = new HashSet<>();
        for (TagDto tag : giftCertificateDto.getTags()) {
            tags.add(tagMapper.toEntity(tagService.addTagIfNotExist(tag)));
        }
        GiftCertificate certificate = mapper.toEntity(giftCertificateDto);
        certificate.setTags(tags);
        Optional<GiftCertificate> optional = repository.add(certificate);
        return mapper.toDto(optional.orElseThrow(() -> new EntityNotAddedException(CERTIFICATE)));
    }

    @Override
    public GiftCertificateDto remove(long id) {
        log.info("remove certificate by id {}", id);
        Optional<GiftCertificate> optional = repository.remove(id);
        return mapper.toDto(optional.orElseThrow(() -> new EntityNotDeletedException(CERTIFICATE, id)));

    }

    @Override
    public GiftCertificateDto update(long certificateId, GiftCertificateDto giftCertificateDto) {
        log.info("update certificate");
        giftCertificateDto.setGiftId(certificateId);
        Optional<GiftCertificate> optional = repository.update(mapper.toEntity(giftCertificateDto));
        return mapper.toDto(optional.orElseThrow(() -> new EntityNotUpdatedException(CERTIFICATE, certificateId)));

    }

    @Override
    public List<GiftCertificateDto> findAll(RequestParametersDto dto) {
        log.info("find gifts");
        List<CriteriaSpecification<GiftCertificate>> specifications = creator.createSpecifications(dto);
        if (specifications.isEmpty()) {
            return mapper.toDtos(repository.findAll(dto.getPage(), dto.getPageLimit()));
        }
        return mapper.toDtos(repository.findAllBySpecification(specifications, dto.getPage(), dto.getPageLimit()));
    }
    @Transactional
    @Override
    public GiftCertificateDto findById(long id) {
        log.info("find by id {}", id);
        Optional<GiftCertificate> optional = repository.findById(id);
        return mapper.toDto(optional.orElseThrow(() -> new EntityNotFoundException(CERTIFICATE, id)));

    }

}
