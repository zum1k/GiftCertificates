package com.epam.esm.service;

public interface GiftCertificateTagService {
    long add(long giftKey, long tagKey);

    int delete(long giftKey, long tagKey);
}
