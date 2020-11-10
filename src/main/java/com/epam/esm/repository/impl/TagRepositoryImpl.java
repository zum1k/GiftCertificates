package com.epam.esm.repository.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.rowmapper.TagRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagRepositoryImpl implements TagRepository {
    private static final String INSERT_INTO_QUERY = "INSERT INTO tags(name) VALUES ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM tags WHERE tag_id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * from tags";
    private static final String SELECT_ALL_TAGS_BY_CERTIFICATE_ID = "SELECT tags.tag_id, tags.name from tags JOIN " +
            "gift_certificate_tag ON gift_certificate_tag.tag = tags.tag_id where gift_certificate_tag.gift = ?";
    private static final String SELECT_BY_NAME_QUERY = "SELECT tags.tag_id, tags.name FROM tags WHERE tags.name = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT tags.tag_id, tags.name FROM tags WHERE tags.tag_id = ?";
    private static final String TAG_ENTITY_NAME = "Tag";

    private final TagRowMapper tagMapper;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public long add(Tag tag) {
        log.info("add tag {}", tag);
        return jdbcTemplate.update(INSERT_INTO_QUERY, tag.getName());
    }

    //TODO
    @Override
    public void delete(long tagId) {
        log.info("delete tag {}", tagId);
        jdbcTemplate.update(DELETE_BY_ID_QUERY, tagId);

    }

    @Override
    public Tag findById(long id) {
        log.info("find tag by id {}", id);
        return jdbcTemplate.query(SELECT_BY_ID_QUERY, tagMapper, id).get(0);
    }

    @Override
    public List<Tag> findAll() {
        log.info("find all tags");
        if () //TODO
            return jdbcTemplate.query(SELECT_ALL_QUERY, tagMapper);
    }

    public List<Tag> findTagsByCertificateId(long certificateId) {
        log.info("find tags by certificate id {}", certificateId);
        //TODO
        return jdbcTemplate.query(SELECT_ALL_TAGS_BY_CERTIFICATE_ID, tagMapper, certificateId);
    }
}


