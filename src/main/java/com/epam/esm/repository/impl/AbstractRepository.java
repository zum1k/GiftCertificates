package com.epam.esm.repository.impl;

import com.epam.esm.repository.Repository;
import com.epam.esm.repository.Specification;
import com.epam.esm.repository.SqlQueryParameter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<T> implements Repository<T> {
    private JdbcTemplate jdbcTemplate;
    private Connection connection;

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long add(T t) {
        return jdbcTemplate.update(); //TODO
    }

    @Override
    public void remove(Specification specification) { //TODO

    }

    @Override
    public void update(T t) { //TODO

    }

    @Override
    public List<T> find(Specification specification) { //TODO
        return null;
    }

    @Override
    public List<T> sort(Comparator<T> comparator) {
        return null;
    }

    protected abstract Map<String, SqlQueryParameter> toEntityFields(T t);
}
