package com.epam.esm.repository.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CertificateRepositoryImpl implements CertificateRepository {
    private static final String INSERT_INTO_QUERY = "INSERT INTO gifts VALUES (?,?,?,?,?,?)";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM gifts WHERE gifts_id = ?";
    private static final String UPDATE_BY_ID_QUERY = "UPDATE gifts set name = ?, set description = ?," +
            "set price = ?, set last_update_date = ?, set duration = ? WHERE gifts_id = ?";
    private static final String SELECT_BY_PART_NAME_QUERY = "SELECT gifts.name, gifts.description, gifts.price," +
            "gifts.create_date,gifts.last_update_date,gifts.duration FROM gifts WHERE name LIKE ?";
    private static final String SELECT_ALL_QUERY = "SELECT * from gifts";
    private static final String SELECT_SORT_BY_NAME_ASC_QUERY = "SELECT * FROM gifts ORDER BY gifts.name ASC";
    private static final String SELECT_ALL_BY_TAG_NAME_QUERY = "SELECT gifts.gifts_id, gifts.name, gifts.description, gifts.price, gifts.create_date," +
            " gifts.last_update_date, gifts.duration FROM gifts JOIN gift_certificate_tag ON gifts.gifts_id = gift_certificate_tag.gift" +
            " JOIN tags ON gift_certificate_tag.tag = tags.tag_id WHERE tags.name = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM gifts WHERE gifts.gifts_id = ?";

    private final RowMapper<GiftCertificate> giftMapper;
    private final JdbcTemplate jdbcTemplate;

    public long add(GiftCertificate giftCertificate) {
        log.info("add certificate");
        return jdbcTemplate.update(INSERT_INTO_QUERY, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getCreateDate(),
                giftCertificate.getLastUpdateDate(), giftCertificate.getDuration());
    }

    @Override
    public void remove(long id) {
        log.info("remove certificate {}", id);
        jdbcTemplate.update(DELETE_BY_ID_QUERY, id);
    }

    @Override
    public void update(long certificateId,GiftCertificate giftCertificate) {
        log.info("update certificate");
        jdbcTemplate.update(UPDATE_BY_ID_QUERY, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getLastUpdateDate(), giftCertificate.getDuration(), certificateId);
    }

    @Override
    public List<GiftCertificate> findByPartName(String partName) {
        log.info("find by part name {}", partName);
        return jdbcTemplate.query(SELECT_BY_PART_NAME_QUERY, new Object[]{partName}, giftMapper);
    }

    @Override
    public List<GiftCertificate> findAll() {
        log.info("find all");
        return jdbcTemplate.query(SELECT_ALL_QUERY, giftMapper);
    }

    @Override
    public List<GiftCertificate> sortByNameASC() {
        log.info("sort by name asc");
        return jdbcTemplate.query(SELECT_SORT_BY_NAME_ASC_QUERY, giftMapper);
    }

    @Override
    public List<GiftCertificate> findByTagName(String tagName) {
        log.info("find by tag name {}", tagName);
        return jdbcTemplate.query(SELECT_ALL_BY_TAG_NAME_QUERY, giftMapper);
    }

    @Override
    public List<GiftCertificate> findById(long id) {
        return jdbcTemplate.query(SELECT_BY_ID_QUERY, giftMapper);
    }

}

