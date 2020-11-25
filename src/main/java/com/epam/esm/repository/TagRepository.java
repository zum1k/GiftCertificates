package com.epam.esm.repository;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Optional<Tag> add(Tag tag);

    Optional<Tag> remove(long id);

    Optional<Tag> findByName(String tagName);

    Optional<Tag> findById(long id);

    List<Tag> findAll();

    List<Tag> findTagsByCertificateId(long certificateId);
}
