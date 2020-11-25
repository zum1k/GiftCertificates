package com.epam.esm.repository.certificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.exception.EntityNotUpdatedException;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.Specification;
import com.epam.esm.repository.rowmapper.CertificateRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CertificateRepositoryImpl implements CertificateRepository {
    private static final String INSERT_INTO_QUERY = "INSERT INTO gifts(name, description, price, create_date, last_update_date,duration) VALUES(:name, :description, :price, :create_date, :last_update_date,:duration)";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM gifts WHERE gifts_id = ?";
    private static final String UPDATE_BY_ID_QUERY = "UPDATE gifts set name = ?, description = ?," +
            "price = ?, last_update_date = ?,duration = ? WHERE gifts_id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT DISTINCT gifts.gifts_id, gifts.name, gifts.description, gifts.price, gifts.create_date ,gifts.last_update_date, gifts.duration " +
            "from gifts JOIN gift_certificate_tag ON gifts.gifts_id = gift_certificate_tag.gift JOIN tags ON gift_certificate_tag.tag = tags.tag_id WHERE 1=1 ";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM gifts WHERE gifts.gifts_id = ?";
    private static final String CERTIFICATE_ENTITY_NAME = "Certificate";

    private final CertificateRowMapper giftMapper;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<GiftCertificate> add(GiftCertificate giftCertificate) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", giftCertificate.getName())
                .addValue("description", giftCertificate.getDescription())
                .addValue("price", giftCertificate.getPrice())
                .addValue("create_date", giftCertificate.getCreateDate())
                .addValue("last_update_date", giftCertificate.getLastUpdateDate())
                .addValue("duration", giftCertificate.getDuration());
        namedParameterJdbcTemplate.update(INSERT_INTO_QUERY, parameters, holder);
        if (holder.getKey() != null) {
            return findById(holder.getKey().longValue());
        }
        throw new EntityNotAddedException(CERTIFICATE_ENTITY_NAME);
    }

    @Override
    public Optional<GiftCertificate> remove(long id) {
        log.info("remove certificate {}", id);
        Optional<GiftCertificate> certificate = findById(id);
        if (jdbcTemplate.update(DELETE_BY_ID_QUERY, id) == 0) {
            throw new EntityNotDeletedException(CERTIFICATE_ENTITY_NAME, id);
        }
        return certificate;
    }

    @Override
    public Optional<GiftCertificate> update(GiftCertificate giftCertificate) {
        log.info("update certificate");
        if (jdbcTemplate.update(UPDATE_BY_ID_QUERY, giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getLastUpdateDate(), giftCertificate.getDuration(), giftCertificate.getCertificateId()) == 0) {
            throw new EntityNotUpdatedException(CERTIFICATE_ENTITY_NAME, giftCertificate.getCertificateId());
        }
        return findById(giftCertificate.getCertificateId());
    }

    @Override
    public List<GiftCertificate> findAllBySpecification(Specification specification) {
        log.info("find all with parameters");
        return jdbcTemplate.query(SELECT_ALL_QUERY + specification.toSqlRequest(), giftMapper, specification.receiveParameters());
    }

    @Override
    public List<GiftCertificate> findAll() {
        log.info("find all");
        return jdbcTemplate.query(SELECT_ALL_QUERY, giftMapper);
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        List<GiftCertificate> resultSet = jdbcTemplate.query(SELECT_BY_ID_QUERY, giftMapper, id);
        return resultSet.size() == 1 ? Optional.of(resultSet.get(0)) : Optional.empty();
    }
}

