package com.epam.esm.repository.certificate;

import com.epam.esm.repository.Specification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CertificatesByNameSpecification implements Specification {
    private final String tagName;

    @Override
    public String toSqlRequest() {
        return " AND tags.name = ?";
    }

    @Override
    public Object[] receiveParameters() {
        return new Object[]{tagName};
    }
}
