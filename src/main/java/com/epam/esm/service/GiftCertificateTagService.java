package com.epam.esm.service;

public interface GiftCertificateTagService {
    long add(long giftKey, long tagKey);

    int remove(long giftKey, long tagKey);
}
