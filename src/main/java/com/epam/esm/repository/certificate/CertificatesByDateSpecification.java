package com.epam.esm.repository.certificate;

import com.epam.esm.repository.Specification;

public class CertificatesByDateSpecification implements Specification {
    private static final String CERTIFICATES_BY_DATE = " AND ORDER BY create_date";


    @Override
    public String toSqlRequest() {
        return CERTIFICATES_BY_DATE;
    }

    @Override
    public Object[] receiveParameters() {
        return new Object[]{};
    }
}

