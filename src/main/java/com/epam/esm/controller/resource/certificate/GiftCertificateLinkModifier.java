package com.epam.esm.controller.resource.certificate;

import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.entity.dto.GiftCertificateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GiftCertificateLinkModifier implements DtoLinkModifier<GiftCertificateDto> {
    @Override
    public GiftCertificateDto withTagLocation(GiftCertificateDto giftCertificateDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<GiftCertificateDto>> allWithPagination(List<GiftCertificateDto> dtos, Integer page, Integer pageSize) {
        return null;
    }
}
