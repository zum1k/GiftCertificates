package com.epam.esm.repository.certificate;

import com.epam.esm.repository.Specification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CertificatesByNameSpecification implements Specification {
    private final String tagName;

    @Override
    public String toSqlRequest() {
        return " JOIN gift_certificate_tag ON gifts.gifts_id = gift_certificate_tag.gift" +
                " JOIN tags ON gift_certificate_tag.tag = tags.tag_id WHERE tags.name = ?";
    }

    @Override
    public Object[] receiveParameters() {
        return new Object[]{tagName};
    }
}
