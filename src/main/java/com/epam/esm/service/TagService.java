package com.epam.esm.service;

import com.epam.esm.entity.dto.TagDto;

import java.util.List;

public interface TagService {

    TagDto addTagIfNotExist(TagDto tagDto);

    TagDto remove(long id);

    TagDto findOne(long id);

    List<TagDto> findAll();

    List<TagDto> findAllByCertificateId(long certificateId);
}
