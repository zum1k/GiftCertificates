package com.epam.esm.repository.rowmapper;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CertificateRowMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate certificate = new GiftCertificate();

        certificate.setCertificateId(rs.getInt("gifts_id"));
        certificate.setName(rs.getString("name"));
        certificate.setDescription(rs.getString("description"));
        certificate.setPrice(rs.getBigDecimal("price"));
        certificate.setCreateDate(rs.getDate("create_date").toLocalDate());
        certificate.setLastUpdateDate(rs.getDate("last_update_date").toLocalDate());
        certificate.setDuration(rs.getInt("duration"));

        return certificate;
    }
}
