package com.epam.esm.repository.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Repository
public class GiftCertificateRepositoryImpl implements GiftRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_INTO = "INSERT INTO GIFTS VALUES (?, ?, ?, ?,?,?)";

    public long add(GiftCertificate giftCertificate) {
        return jdbcTemplate.update(INSERT_INTO, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getCreateDate(),
                giftCertificate.getLastUpdateDate(), giftCertificate.getDuration());
    }

    @Override
    public void remove(long id) {
        jdbcTemplate.
    }

    @Override
    public void update(GiftCertificate giftCertificate) {
    }

    @Override
    public List<GiftCertificate> findByName(String name) {
        return null;
    }

    @Override
    public List<GiftCertificate> findByCreateDate(Date date) {
        return null;
    }

    @Override
    public List<GiftCertificate> findByLastUpdateDate(Date date) {
        return null;
    }

    @Override
    public List<GiftCertificate> findByDuration(int days) {
        return null;
    }

    @Override
    public List<GiftCertificate> sort(Comparator<GiftCertificate> comparator) {
        return null;
    }
}

