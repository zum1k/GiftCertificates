package com.epam.esm.service;

public interface GiftCertificateTagService {
    long add(long giftKey, long tagKey);

    void remove(long giftKey, long tagKey);
}
