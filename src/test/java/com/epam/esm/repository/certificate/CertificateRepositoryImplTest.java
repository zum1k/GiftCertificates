package com.epam.esm.repository.certificate;

import com.epam.esm.configuration.DBTestConfig;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.CertificateRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DBTestConfig.class})
public class CertificateRepositoryImplTest {

    private final CertificateRepository repository;

    private static final String FIRST_NAME_UPD = "name1upd";
    private static final String ELEVENTH_NAME = "name11";

    @Autowired
    public CertificateRepositoryImplTest(CertificateRepository repository) {
        this.repository = repository;
    }

    @Test
    void addCertificate_shouldReturnIdSix_true_test() {
        final GiftCertificate CERTIFICATE = new GiftCertificate(ELEVENTH_NAME, "Description 11", new BigDecimal(100.1),
                LocalDateTime.now().toString(), LocalDateTime.now().toString(), 8);

        long expectedCertificateId = 6;

        Optional<GiftCertificate> giftCertificate = repository.add(CERTIFICATE);
        long actualCertificateId = giftCertificate.get().getCertificateId();


        Assert.assertEquals(expectedCertificateId, actualCertificateId);
    }

    @Test
    void shouldRemove() {
    }

    @Test
    void shouldUpdate() {
    }

    @Test
    void shouldFindAllBySpecification() {
    }

    @Test
    void shouldFindAll() {
    }

    @Test
    void shouldFindById() {
    }
}