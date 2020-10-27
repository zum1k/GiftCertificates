package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public interface GiftRepository {
    public long add(GiftCertificate giftCertificate);

    public void remove(long id);

    public void update(GiftCertificate giftCertificate);

    public List<GiftCertificate> findByName(String name);

    public List<GiftCertificate> findByCreateDate(Date date);

    public List<GiftCertificate> findByLastUpdateDate(Date date);

    public List<GiftCertificate> findByDuration(int days);

    public List<GiftCertificate> sort(Comparator<GiftCertificate> comparator);
}
