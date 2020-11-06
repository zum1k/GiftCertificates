package com.epam.esm.service;

import com.epam.esm.entity.dto.TagDto;

import java.util.List;

public interface TagService {
    public long add(TagDto tagDto);

    public void remove(long id);

    public TagDto findOne(long id);

    public List<TagDto> findAll();
}
