package com.epam.esm.repository.tag;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Optional<Tag> add(Tag tag);

    Optional<Tag> remove(long id);

    Optional<Tag> findByName(String tagName);

    Tag findById(long id);

    List<Tag> findAll();

    List<Tag> findTagsByCertificateId(long certificateId);
}
