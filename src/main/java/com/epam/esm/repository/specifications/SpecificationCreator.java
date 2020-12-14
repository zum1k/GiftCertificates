package com.epam.esm.repository.specifications;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.repository.CriteriaSpecification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SpecificationCreator {
    public List<CriteriaSpecification<GiftCertificate>> createSpecifications(RequestParametersDto dto){
        List<CriteriaSpecification<GiftCertificate>> specifications = new ArrayList<>();
        if(dto.getTagName()!=null){
            specifications.add(new CertificatesByTagNameCriteriaSpecification(dto.getTagName()));
        }
        if(dto.getPartName()!=null){
            specifications.add(new CertificatesByPartNameCriteriaSpecification(dto.getPartName()));
        }
        if(dto.getPartDescription()!=null){
            specifications.add(new CertificatesByPartDescriptionCriteriaSpecification(dto.getPartDescription()));
        }
        return specifications;
    }
}
