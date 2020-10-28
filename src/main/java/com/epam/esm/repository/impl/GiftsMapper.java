package com.epam.esm.repository.impl;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftsMapper implements RowMapper<GiftCertificate> {
    @Bean
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();

        giftCertificate.setGiftId(rs.getInt("gift_id"));
        giftCertificate.setName(rs.getString("name"));
        giftCertificate.setDescription(rs.getString("description"));
        giftCertificate.setPrice(rs.getBigDecimal("price"));
        giftCertificate.setCreateDate(rs.getDate("create_date"));
        giftCertificate.setLastUpdateDate(rs.getDate("last_update_date"));
        giftCertificate.setDuration(rs.getInt("duration"));

        return giftCertificate;
    }
}
