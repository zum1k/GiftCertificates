package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.CriteriaSpecification;
import com.epam.esm.repository.specification.TagsByCertificateIdCriteriaSpecifications;
import com.epam.esm.repository.specification.TagsByNameCriteriaSpecifications;
import com.epam.esm.repository.tag.TagRepository;
import com.epam.esm.service.TagService;
import com.epam.esm.service.mapper.tag.TagMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TagServiceImpl.class);

    private static final String TAG = "Tag";
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    @Transactional
    @Override
    public TagDto addTagIfNotExist(TagDto tag) {
        log.info("add tag");
        Optional<TagDto> tagOptional = findByName(tag);
        if (tagOptional.isEmpty()) {
            return tagMapper.toDto(tagRepository.add(tagMapper.toEntity(tag)).get());
        }
        return tagOptional.get();
    }
    @Transactional
    @Override
    public TagDto remove(long id) {
        log.info("remove tag {}", id);
        Optional<Tag> tagOptional = tagRepository.remove(id);
        return tagMapper.toDto(tagOptional.orElseThrow(()-> new EntityNotDeletedException(TAG, id)));
    }

    @Override
    public TagDto findOne(long id) {
        log.info("find tag {}", id);
        Tag tag = tagRepository.findById(id);
        return tagMapper.toDto(tag);
    }

    @Override
    public List<TagDto> findAll(RequestParametersDto dto) {
        log.info("find tags ");
        if (tagRepository.findAll(dto.getPage(), dto.getPageLimit()).isEmpty()) {
            throw new EntityNotFoundException(TAG, 0);
        }
        return tagMapper.toDtoList(tagRepository.findAll(1, 3));
    }

    @Override
    public Optional<TagDto> findByName(TagDto tagDto) {
        log.info("find by name {}", tagDto.getName());
        CriteriaSpecification<Tag> specification = new TagsByNameCriteriaSpecifications(tagDto.getName());
        Optional<Tag> optionalTag = tagRepository.findTagByName(specification);
        if (optionalTag.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(tagMapper.toDto(optionalTag.get()));
    }

    @Override
    public List<TagDto> findAllByCertificateId(long certificateId) {
        CriteriaSpecification<Tag> specification = new TagsByCertificateIdCriteriaSpecifications(certificateId);
        return tagMapper.toDtoList(tagRepository.findTagsByCertificateId(specification));
    }

    @Override
    public int count(int pageSize) {
        return tagRepository.findAll().size();
    }
}
