package com.epam.esm.repository.impl;

import com.epam.esm.repository.GiftCertificateRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {
    @Override
    public void delete() {
    }

    @Override
    public void create() {
    }

    @Override
    public long[] findByGiftTagKeys(long giftKey, long tagKey) {
        return new long[0];
    }
}
