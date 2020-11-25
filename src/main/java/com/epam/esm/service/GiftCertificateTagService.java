package com.epam.esm.service;

public interface GiftCertificateTagService {
    long add(long giftKey, long tagKey);

    long remove(long giftKey, long tagKey);
}
