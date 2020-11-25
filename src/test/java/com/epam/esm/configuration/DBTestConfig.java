package com.epam.esm.configuration;

import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.certificate.CertificateRepositoryImpl;
import com.epam.esm.repository.certificate.SpecificationCreator;
import com.epam.esm.repository.rowmapper.CertificateRowMapper;
import com.epam.esm.repository.rowmapper.TagRowMapper;
import com.epam.esm.repository.tag.TagRepositoryImpl;
import com.epam.esm.repository.tagcertificate.GiftCertificateRepositoryImpl;
import com.epam.esm.service.GiftCertificateTagService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.GIftCertificateTagServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import com.epam.esm.service.mapper.tag.TagMapper;
import com.epam.esm.service.mapper.tag.TagMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DBTestConfig {

    private static final String SCRIPT_ENCODING = "UTF-8";

    @Bean
    public DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding(SCRIPT_ENCODING)
                .addScript("db/schema.sql")
                .addScript("db/values.sql")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(h2DataSource());
    }

    @Bean
    public TagRowMapper tagRowMapper() {
        return new TagRowMapper();
    }

    @Bean
    public TagRepository tagRepository(TagRowMapper tagRowMapper) {
        return new TagRepositoryImpl(tagRowMapper, jdbcTemplate(), namedParameterJdbcTemplate());
    }

    @Bean
    public GiftCertificateRepository giftCertificateRepository() {
        return new GiftCertificateRepositoryImpl(jdbcTemplate());
    }

    @Bean
    public CertificateRowMapper certificateMapper() {
        return new CertificateRowMapper();
    }

    @Bean
    public CertificateRepository certificateRepository(CertificateRowMapper giftCertificateMapper) {
        return new CertificateRepositoryImpl(giftCertificateMapper, jdbcTemplate(), namedParameterJdbcTemplate());
    }

    @Bean
    public SpecificationCreator specificationCreator() {
        return new SpecificationCreator();
    }

    @Bean
    public TagMapper tagMapper() {
        return new TagMapperImpl();
    }

    @Bean
    public TagService tagService() {
        return new TagServiceImpl(tagRepository(tagRowMapper()), tagMapper());
    }

    @Bean
    public GiftCertificateTagService giftCertificateTagService() {
        return new GIftCertificateTagServiceImpl(giftCertificateRepository());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(h2DataSource());
    }

}

