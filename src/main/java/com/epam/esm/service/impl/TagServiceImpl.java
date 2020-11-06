package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityAlreadyExists;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import com.epam.esm.service.mapper.tag.TagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagServiceImpl implements TagService {
    private static final int ZERO_NUMBER = 0;
    private static final String TAG = "Tag";

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public long add(TagDto tag) {
        log.info("add tag");
        if (tagRepository.findByName(tag.getName()).isEmpty()) {
            return tagRepository.add(tagMapper.toEntity(tag));
        }
        throw new EntityAlreadyExists(TAG);
    }

    @Override
    public void remove(long id) {
        log.info("remove tag {}", id);
        if (tagRepository.findOne(id).isEmpty()) {
            throw new EntityNotFoundException(TAG);
        }
        tagRepository.delete(id);
    }

    @Override
    public TagDto findOne(long id) {
        log.info("find tag {}", id);
        if (tagRepository.findOne(id).isEmpty()) {
            throw new EntityNotFoundException(TAG);
        }
        return tagMapper.toDto(tagRepository.findOne(id).get(ZERO_NUMBER));
    }

    @Override
    public List<TagDto> findAll() {
        log.info("find tags ");
        if (tagRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException(TAG);
        }
        return tagMapper.toDtoList(tagRepository.findAll());
    }
}
