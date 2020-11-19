package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import com.epam.esm.service.mapper.tag.TagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagServiceImpl implements TagService {
    private static final String TAG = "Tag";

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagDto addTagIfNotExist(TagDto tag) {
        log.info("add tag");
        Optional<Tag> tagOptional = tagRepository.findByName(tag.getName());
        if (tagOptional.isEmpty()) {
            return tagMapper.toDto(tagRepository.add(tagMapper.toEntity(tag)).get());
        }
        return tagMapper.toDto(tagOptional.get());
    }

    @Override
    public TagDto remove(long id) {
        log.info("remove tag {}", id);
        return tagMapper.toDto(tagRepository.remove(id).get());
    }

    @Override
    public TagDto findOne(long id) {
        log.info("find tag {}", id);
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (optionalTag.isEmpty()) {
            throw new EntityNotFoundException(TAG);
        }
        return tagMapper.toDto(optionalTag.get());
    }

    @Override
    public List<TagDto> findAll() {
        log.info("find tags ");
        if (tagRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException(TAG);
        }
        return tagMapper.toDtoList(tagRepository.findAll());
    }

    @Override
    public List<TagDto> findAllByCertificateId(long certificateId) {
        return tagMapper.toDtoList(tagRepository.findTagsByCertificateId(certificateId));
    }
}
