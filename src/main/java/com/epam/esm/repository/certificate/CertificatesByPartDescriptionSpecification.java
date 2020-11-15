package com.epam.esm.repository.certificate;

import com.epam.esm.repository.Specification;


public class CertificatesByPartDescriptionSpecification implements Specification {
    private final String descriptionPart;

    public CertificatesByPartDescriptionSpecification(String descriptionPart) {
        this.descriptionPart = "%"+descriptionPart+"%";
    }

    @Override
    public String toSqlRequest() {
        return " AND gifts.description LIKE ?";
    }

    @Override
    public Object[] receiveParameters() {
        return new Object[]{descriptionPart};
    }
}
