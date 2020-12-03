package com.epam.esm.repository.certificate;

import com.epam.esm.entity.GiftCertificate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
// @ContextConfiguration(classes = {DBTestConfig.class})
@ExtendWith(SpringExtension.class)
public class CertificateRepositoryImplTest {
    private final CertificateRepository repository;

    @Test
    void findAll_ShouldReturn_Five_True_Test() {
        //TODO
        int actualRows = repository.findAll().size();
        assertTrue(actualRows > 0);
    }

    @Test
    void findAllCertificates_ShouldReturnSix_True_Test() {
//        //TODO
//        Specification specification = new CertificatesByPartNameSpecification("name");
//        int actualRows = repository.findAllBySpecification(specification).size();
//        assertTrue(actualRows > 0);
    }

    @Test
    void addCertificate_ShouldReturnIdSix_True_Test() {
        final GiftCertificate expectedCertificate = new GiftCertificate("name11", "Description 11", new BigDecimal("100.10"), ZonedDateTime.now().withFixedOffsetZone(), ZonedDateTime.now().withFixedOffsetZone(), 8);
        Optional<GiftCertificate> giftCertificate = repository.add(expectedCertificate);
        String actualCertificateName = giftCertificate.get().getName();
        assertEquals(expectedCertificate.getName(), actualCertificateName);
    }

    @Test
    void removeCertificate_ShouldReturnFive_True_Test() {
        long expectedRemovedCertificateId = 5;
        long actualRemovedCertificateId = repository.remove(expectedRemovedCertificateId).get().getId();
        assertEquals(expectedRemovedCertificateId, actualRemovedCertificateId);
    }

    @Test
    void updateCertificate_ShouldReturnTrue_Test() {
        String exceptedName = "name1updated";
        final GiftCertificate expectedCertificate = new GiftCertificate(exceptedName, "Description 11", new BigDecimal("100.10"), ZonedDateTime.now().withFixedOffsetZone(), ZonedDateTime.now().withFixedOffsetZone(), 8);
        expectedCertificate.setId(1);
        String actualCertificateName = repository.update(expectedCertificate).get().getName();
        assertEquals(exceptedName, actualCertificateName);
    }

    @Test
    void findCertificateById_ShouldReturn_Two_True_Test() {
        String expectedName = "name2";
        long expectedCertificateId = 2;
        String actualCertificateName = repository.findById(expectedCertificateId).get().getName();
        assertEquals(expectedName, actualCertificateName);
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @Autowired
    @SuppressWarnings("all")
    public CertificateRepositoryImplTest(final CertificateRepository repository) {
        this.repository = repository;
    }
    //</editor-fold>
}
