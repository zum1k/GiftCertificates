package com.epam.esm.repository.specifications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CertificatesBySpecifications implements Specification {
    private final List<Specification> specificationList;

    @Override
    public String toSqlRequest() {
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (Specification specification : specificationList) {
            stringBuilder.append(specification.toSqlRequest());
        }
        return stringBuilder.toString();
    }

    @Override
    public Object[] receiveParameters() {
        List<Object> parameters = new ArrayList<>();
        for (Specification specification : specificationList) {
            parameters.addAll(Arrays.asList(specification.receiveParameters()));
        }
        return parameters.toArray();
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public CertificatesBySpecifications(final List<Specification> specificationList) {
        this.specificationList = specificationList;
    }
    //</editor-fold>
}
