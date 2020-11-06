package com.epam.esm.repository;

public interface GiftCertificateRepository {
    void delete(long giftKey, long tagKey);

    long add(long giftKey, long tagKey);
}
