package com.epam.esm.repository;

public interface GiftCertificateRepository {
    public void delete();

    public void create();

    public long[] findByGiftTagKeys(long giftKey, long tagKey);
}
