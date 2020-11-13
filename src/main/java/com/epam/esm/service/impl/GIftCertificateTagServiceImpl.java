package com.epam.esm.service.impl;

import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.service.GiftCertificateTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GIftCertificateTagServiceImpl implements GiftCertificateTagService {
    private GiftCertificateRepository giftCertificateRepository;

    @Override
    public long add(long giftKey, long tagKey) {
        return 0;
    }

    @Override
    public int remove(long giftKey, long tagKey) {
        return 0;
    }


}
