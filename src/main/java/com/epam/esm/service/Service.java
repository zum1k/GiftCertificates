package com.epam.esm.service;

import java.util.List;

public interface Service<T> {
    public long add();
    public List<T> find();
    public List<T> sortByName();

}
