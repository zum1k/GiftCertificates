package com.epam.esm.repository.impl;

import com.epam.esm.bean.Certificate;
import com.epam.esm.repository.Repository;
import com.epam.esm.repository.Specification;

import java.util.Comparator;
import java.util.List;

public class CertificateRepositoryImpl implements Repository<Certificate> {

    @Override
    public void add(Certificate certificate) {
    }

    @Override
    public void remove(Specification specification) {
    }

    @Override
    public void update(Certificate certificate) {
    }

    @Override
    public List<Certificate> find(Specification specification) {
        return null;
    }

    @Override
    public List<Certificate> sort(Comparator<Certificate> comparator) {
        return null;
    }
}

