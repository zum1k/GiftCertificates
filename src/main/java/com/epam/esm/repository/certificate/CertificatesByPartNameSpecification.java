package com.epam.esm.repository.certificate;

import com.epam.esm.repository.Specification;


public class CertificatesByPartNameSpecification implements Specification {
    private final String partName;

    public CertificatesByPartNameSpecification(String partName) {
        this.partName = "%"+partName+"%";
    }

    @Override
    public String toSqlRequest() {
        return " AND gifts.name LIKE ?";
    }

    @Override
    public Object[] receiveParameters() {
        return new Object[]{partName};
    }
}
