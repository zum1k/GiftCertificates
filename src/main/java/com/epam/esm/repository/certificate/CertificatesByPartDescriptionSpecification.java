package com.epam.esm.repository.certificate;

import com.epam.esm.repository.Specification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CertificatesByPartDescriptionSpecification implements Specification {
    private final String descriptionPart;

    @Override
    public String toSqlRequest() {
        return " WHERE gifts.description LIKE ?";
    }

    @Override
    public Object[] receiveParameters() {
        return new Object[]{descriptionPart};
    }
}
