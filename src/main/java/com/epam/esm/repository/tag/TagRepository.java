package com.epam.esm.repository.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.CriteriaSpecification;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Optional<Tag> add(Tag tag);

    Optional<Tag> remove(long id);

    Optional<Tag> findTagByName(CriteriaSpecification<Tag> specification);

    Tag findById(long id);

    List<Tag> findAll(int page, int pageSize);

    List<Tag> findTagsByCertificateId(CriteriaSpecification<Tag> specification);

    List<Tag> findAll();
}
