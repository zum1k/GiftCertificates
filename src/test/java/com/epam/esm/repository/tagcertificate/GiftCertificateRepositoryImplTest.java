package com.epam.esm.repository.tagcertificate;

import com.epam.esm.configuration.DBTestConfig;
import com.epam.esm.repository.GiftCertificateRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DBTestConfig.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class GiftCertificateRepositoryImplTest {
    private final GiftCertificateRepositoryImpl repository;

    @Test
    void deleteRow_ShouldReturn_True_Test() {
        long giftId = 1;
        long tagId = 1;
        long actualRow  = repository.remove(giftId, tagId);
        Assertions.assertTrue(actualRow > 0);
    }

    @Test
    void addRow_ShouldReturn_True_Test() {
        long giftId = 1;
        long tagId = 1;
        long actualRow  = repository.remove(giftId, tagId);
        Assertions.assertTrue(actualRow > 0);
    }
}