package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {
    public long add(Tag tag);

    public void remove(long id);

    public Tag findOne(long id);

    public List<Tag> findAll();
}
