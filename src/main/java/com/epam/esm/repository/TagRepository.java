package com.epam.esm.repository;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagRepository {
    public long add(Tag tag);

    public void remove(long id);

    public List<Tag> findOne(long id);

    public List<Tag> findAll();
}
