package com.epam.esm.repository.impl;

import com.epam.esm.repository.GiftCertificateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {
    private static final String INSERT_INTO_QUERY = "INSERT INTO gift_certificate_tag VALUES (?,?)";
    private static final String DELETE_BY_IDs_QUERY = "DELETE FROM gift_certificate_tag WHERE gift = ? AND tag = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void delete(long giftKey, long tagKey) {
        log.info("delete keys {},{}", giftKey, tagKey);
        jdbcTemplate.update(DELETE_BY_IDs_QUERY, giftKey, tagKey);
    }

    @Override
    public long add(long giftKey, long tagKey) {
        log.info("add keys {},{},", giftKey, tagKey);
        return jdbcTemplate.update(INSERT_INTO_QUERY, giftKey, tagKey);
    }
}