package com.epam.esm.repository;

public interface SqlQueryParameter<T> {
    T getValue();
    String getType();
}
