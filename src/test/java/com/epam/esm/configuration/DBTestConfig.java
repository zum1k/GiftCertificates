package com.epam.esm.configuration;

import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.certificate.CertificateRepositoryImpl;
import com.epam.esm.repository.rowmapper.CertificateRowMapper;
import com.epam.esm.repository.rowmapper.TagRowMapper;
import com.epam.esm.repository.tag.TagRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
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
    public TagRowMapper tagMapper() {
        return new TagRowMapper();
    }

    @Bean
    public TagRepository tagRepository(TagRowMapper tagRowMapper) {
        return new TagRepositoryImpl(tagRowMapper, jdbcTemplate(), namedParameterJdbcTemplate());
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
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(h2DataSource());
    }

}

