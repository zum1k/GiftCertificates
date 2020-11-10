package com.epam.esm.repository.certificate;

import com.epam.esm.repository.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecificationBuilder implements Specification {
    List<Specification> specificationList;

    public SpecificationBuilder(List<Specification> specificationList) {
        this.specificationList = specificationList;
    }

    @Override
    public String toSqlRequest() {
        StringBuilder stringBuilder = new StringBuilder(" WHERE 1=1 ");
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
}
