package com.epam.esm.repository.rowmapper;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        certificate.setCreateDate(rs.getString("create_date"));
        certificate.setLastUpdateDate(rs.getString("last_update_date"));
        certificate.setDuration(rs.getInt("duration"));

        return certificate;
    }
}
