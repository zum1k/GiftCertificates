package com.epam.esm.repository.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepositoryImpl implements TagRepository {
    @Override
    public long add(Tag tag) {
        return 0;
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public List<Tag> findOne(long id) {
        return null;
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }
}


