package com.epam.esm.repository.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.GiftRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Repository
public class GiftCertificateRepositoryImpl implements GiftRepository {
    private static final String INSERT_INTO = "INSERT INTO GIFTS VALUES (?, ?, ?, ?,?,?)";
    private static final String

    @Override
    public long add(GiftCertificate giftCertificate) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        return jdbcTemplate.update(INSERT_INTO, );
    }

    @Override
    public void remove() {
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

