package com.epam.esm.repository;

public interface GiftCertificateRepository {
    long remove(long giftKey, long tagKey);

    long add(long giftKey, long tagKey);
}
