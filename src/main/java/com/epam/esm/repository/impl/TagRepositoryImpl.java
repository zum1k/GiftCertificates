package com.epam.esm.repository.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepositoryImpl implements TagRepository {
    private static final String INSERT_INTO_QUERY = "INSERT INTO tags VALUES ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM tags WHERE tag_id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * from tags";
    private static final String SELECT_ALL_TAGS_BY_CERTIFICATE_ID = "SELECT tags.tag_id, tags.name from tags JOIN " +
            "gift_certificate_tag ON gift_certificate_tag.tag = tags.tag_id where gift_certificate_tag.gift = ?";

    private final RowMapper<Tag> tagMapper;
    private final JdbcTemplate jdbcTemplate;

    public TagRepositoryImpl(RowMapper<Tag> tagMapper, JdbcTemplate jdbcTemplate) {
        this.tagMapper = tagMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long add(Tag tag) {
        return jdbcTemplate.update(INSERT_INTO_QUERY, tag.getName());
    }

    @Override
    public void remove(long tagId) {
        jdbcTemplate.update(DELETE_BY_ID_QUERY, tagId);
    }

    @Override
    public Tag findOne(long id) {
        return null;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, tagMapper);
    }

    public List<Tag> findTagsByCertificateId(long certificateId) {
        return jdbcTemplate.query(SELECT_ALL_TAGS_BY_CERTIFICATE_ID, tagMapper);
    }
}


