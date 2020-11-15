package com.epam.esm.repository.certificate;

import com.epam.esm.entity.DateSortType;
import com.epam.esm.repository.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpecificationCreator {
    public Optional<Specification> receiveSpecification(String tagName, String partName, String partDescription, DateSortType type) {
        List<Specification> specifications = new ArrayList<>();
        if (tagName != null) {
            Specification specification = new CertificatesByNameSpecification(tagName);
            specifications.add(specification);
        }
        if (partName != null) {
            Specification specification = new CertificatesByPartNameSpecification(partName);
            specifications.add(specification);
        }
        if (partDescription != null) {
            Specification specification = new CertificatesByPartDescriptionSpecification(partDescription);
            specifications.add(specification);
        }
        if (type != null) {
            Specification specification = new CertificatesByDateSpecification(type);
            specifications.add(specification);
        }
        if (specifications.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new CertificatesBySpecifications(specifications));
    }
}
