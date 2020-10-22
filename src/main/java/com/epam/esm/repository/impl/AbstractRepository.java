package com.epam.esm.repository.impl;

import com.epam.esm.repository.Repository;
import com.epam.esm.repository.Specification;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractRepository<T> implements Repository<T> {
    @Override
    public void add(T t) {

    }

    @Override
    public void remove(Specification specification) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public List<T> find(Specification specification) {
        return null;
    }

    @Override
    public List<T> sort(Comparator<T> comparator) {
        return null;
    }
}
