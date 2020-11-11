package com.epam.esm.repository.certificate;

import com.epam.esm.repository.Specification;

public class CertificatesByPartDescriptionSpecification implements Specification {
    private final String CERTIFICATES_BY_DESCRIPTION_PART = " WHERE gifts.description LIKE ?";
    private final String descriptionPart;

    public CertificatesByPartDescriptionSpecification(String descriptionPart) {
        this.descriptionPart = descriptionPart;
    }

    @Override
    public String toSqlRequest() {
        return null;
    }

    @Override
    public Object[] receiveParameters() {
        return new Object[]{descriptionPart};
    }
}
