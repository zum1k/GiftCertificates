package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Slf4j
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public long add(Tag tag) {
        log.info("add tag");
        return tagRepository.add(tag);
    }

    @Override
    public void remove(long id) {
        log.info("remove tag {}", id);
        tagRepository.remove(id);
    }

    @Override
    public Tag findOne(long id) {
        log.info("find tag {}", id);
        return tagRepository.findOne(id);
    }

    @Override
    public List<Tag> findAll() {
        log.info("find tags ");
        return tagRepository.findAll();
    }
}
