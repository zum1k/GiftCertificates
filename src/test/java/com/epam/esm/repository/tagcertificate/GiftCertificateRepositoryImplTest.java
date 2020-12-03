package com.epam.esm.repository.tagcertificate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//@ContextConfiguration(classes = {DBTestConfig.class})
@ExtendWith(SpringExtension.class)
class GiftCertificateRepositoryImplTest {
    private final GiftCertificateRepositoryImpl repository;

    @Test
    void deleteRow_ShouldReturn_True_Test() {
        long giftId = 2;
        long tagId = 2;
        long actualRow = repository.remove(giftId, tagId);
        Assertions.assertTrue(actualRow > 0);
    }

    @Test
    void addRow_ShouldReturn_True_Test() {
        long giftId = 2;
        long tagId = 2;
        long actualRow = repository.add(giftId, tagId);
        Assertions.assertTrue(actualRow > 0);
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @Autowired
    @SuppressWarnings("all")
    public GiftCertificateRepositoryImplTest(final GiftCertificateRepositoryImpl repository) {
        this.repository = repository;
    }
    //</editor-fold>
}
