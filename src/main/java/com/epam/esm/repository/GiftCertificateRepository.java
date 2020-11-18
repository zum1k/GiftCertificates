package com.epam.esm.repository;

public interface GiftCertificateRepository {
    long delete(long giftKey, long tagKey);

    long add(long giftKey, long tagKey);
}
