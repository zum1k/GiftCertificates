package com.epam.esm.repository.certificate;

import com.epam.esm.configuration.DBTestConfig;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.Specification;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CertificateRepositoryImplTest {
    private final CertificateRepository repository;

    @Test
    void findAll_ShouldReturn_Five_True_Test() {
        int actualRows = repository.findAll().size();
        Assert.assertTrue(actualRows > 0);
    }

    @Test
    void findAllCertificates_ShouldReturnSix_True_Test() {
        Specification specification = new CertificatesByPartNameSpecification("name");
        int actualRows = repository.findAllBySpecification(specification).size();
        Assert.assertTrue(actualRows > 0);
    }

    @Test
    void addCertificate_ShouldReturnIdSix_True_Test() {
        final GiftCertificate CERTIFICATE = new GiftCertificate("name11", "Description 11", new BigDecimal("100.10"),
                LocalDateTime.now().toString(), LocalDateTime.now().toString(), 8);
        long expectedCertificateId = 6;
        Optional<GiftCertificate> giftCertificate = repository.add(CERTIFICATE);
        long actualCertificateId = giftCertificate.get().getCertificateId();
        Assert.assertEquals(expectedCertificateId, actualCertificateId);
    }

    @Test
    void removeCertificate_ShouldReturnFive_True_Test() {
        long expectedRemovedCertificateId = 5;
        long actualRemovedCertificateId = repository.remove(5).get().getCertificateId();
        Assert.assertEquals(expectedRemovedCertificateId, actualRemovedCertificateId);
    }

    @Test
    void updateCertificate_ShouldReturnTrue_Test() {
        String exceptedName = "name1updated";
        final GiftCertificate expectedCertificate = new GiftCertificate(exceptedName, "Description 11", new BigDecimal("100.10"),
                LocalDateTime.now().toString(), LocalDateTime.now().toString(), 8);
        expectedCertificate.setCertificateId(1);
        String actualCertificateName = repository.update(expectedCertificate).get().getName();
        Assert.assertEquals(exceptedName, actualCertificateName);
    }

    @Test
    void findCertificateById_ShouldReturn_Two_True_Test() {
        String expectedName = "name2";
        long expectedCertificateId = 2;
        String actualCertificateName = repository.findById(expectedCertificateId).get().getName();
        Assert.assertEquals(expectedName, actualCertificateName);
    }
}