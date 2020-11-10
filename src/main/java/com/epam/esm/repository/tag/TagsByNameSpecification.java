package com.epam.esm.repository.tag;

import com.epam.esm.repository.Specification;

public class TagsByNameSpecification implements Specification {
    @Override
    public String toSqlRequest() {
        return null;
    }

    @Override
    public Object[] receiveParameters() {
        return new Object[0];
    }
}
