package com.epam.esm.repository.certificate;

import com.epam.esm.repository.Specification;

public class CertificatesByPartNameSpecification implements Specification {
    @Override
    public String toSqlRequest() {
        return null;
    }

    @Override
    public Object[] receiveParameters() {
        return new Object[0];
    }
}