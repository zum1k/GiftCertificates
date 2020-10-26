package com.epam.esm.repository;

import java.util.Comparator;
import java.util.List;

public interface Repository<T> {
    public long add(T t);

    public void remove(Specification specification);

    public void update(T t);

    public List<T> find(Specification specification);

    public List<T> sort(Comparator<T> comparator);
}
