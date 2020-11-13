package com.epam.esm.repository.certificate;

import com.epam.esm.repository.Specification;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class SpecificationBuilder implements Specification {
    List<Specification> specificationList;

    @Override
    public String toSqlRequest() {
        StringBuilder stringBuilder = new StringBuilder("");
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
