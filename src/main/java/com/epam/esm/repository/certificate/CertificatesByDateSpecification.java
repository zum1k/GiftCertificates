package com.epam.esm.repository.certificate;

import com.epam.esm.entity.DateSortType;
import com.epam.esm.repository.Specification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CertificatesByDateSpecification implements Specification {
    private final DateSortType dateSortType;

    @Override
    public String toSqlRequest() {
        return " AND ORDER BY create_date " + dateSortType;
    }

    @Override
    public Object[] receiveParameters() {
        return new Object[]{dateSortType.getValue()};
    }
}

