package com.epam.esm.repository;

import java.util.Map;

public interface SqlMapper<T> {
    Map<String, SqlQueryParameter> toEntityFields();
}
