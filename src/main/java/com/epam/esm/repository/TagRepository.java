package com.epam.esm.repository;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagRepository {
    long add(Tag tag);

    void delete(long id);

    List<Tag> findOne(long id);

    List<Tag> findAll();

    List<Tag> findTagsByCertificateId(long certificateId);
}
