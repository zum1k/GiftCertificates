package com.epam.esm.repository.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GiftCertificateRepositoryImpl implements GiftRepository {
    private static final String INSERT_INTO_QUERY = "INSERT INTO gifts VALUES (?,?,?,?,?,?)";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM gifts WHERE gifts_id = ?";
    private static final String UPDATE_BY_ID_QUERY = "UPDATE gifts set name = ?, set description = ?," +
            "set price = ?, set last_update_date = ?, set duration = ? WHERE gifts_id = ?";
    private static final String SELECT_BY_PART_NAME_QUERY = "SELECT gifts.name, gifts.description, gifts.price," +
            "gifts.create_date,gifts.last_update_date,gifts.duration FROM gifts WHERE name LIKE ?";
    private static final String SELECT_BY_PART_DESCRIPTION_QUERY = "SELECT gifts.name, gifts.description, gifts.price," +
            "gifts.create_date,gifts.last_update_date,gifts.duration FROM gifts WHERE description LIKE ?";
    private static final String SELECT_ALL_QUERY = "SELECT * from gifts";

    private final RowMapper<GiftCertificate> giftMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateRepositoryImpl(JdbcTemplate jdbcTemplate, RowMapper<GiftCertificate> giftMapper) {
        this.giftMapper = giftMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    public long add(GiftCertificate giftCertificate) {
        return jdbcTemplate.update(INSERT_INTO_QUERY, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getCreateDate(),
                giftCertificate.getLastUpdateDate(), giftCertificate.getDuration());
    }

    @Override
    public void remove(long id) {
        jdbcTemplate.update(DELETE_BY_ID_QUERY, id);
    }

    @Override
    public void update(GiftCertificate giftCertificate) {
        jdbcTemplate.update(UPDATE_BY_ID_QUERY, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getLastUpdateDate(), giftCertificate.getDuration());
    }

    @Override
    public List<GiftCertificate> findByPartName(String partName) {
        return jdbcTemplate.query(SELECT_BY_PART_NAME_QUERY, new Object[]{partName}, giftMapper);
    }

    @Override
    public List<GiftCertificate> findByDescriptionPart(String descriptionPart) {
        return jdbcTemplate.query(SELECT_BY_PART_DESCRIPTION_QUERY, new Object[]{descriptionPart}, giftMapper);
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, giftMapper);
    }

}

