package com.epam.esm.repository;

import java.util.Map;

public interface RowMapper<T> {
    Map<String, SqlQueryParameter> toEntityFields();
}
