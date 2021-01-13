package com.epam.esm.controller.resource.certificate;

import com.epam.esm.controller.GiftsController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.controller.resource.tag.TagLinkModifier;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GiftCertificateLinkModifier implements DtoLinkModifier<GiftCertificateDto> {
    private static final GiftsController controller =
            WebMvcLinkBuilder.methodOn(GiftsController.class);
    private final TagLinkModifier tagLinkModifier;

    @Override
    public GiftCertificateDto withTagLocation(GiftCertificateDto giftCertificateDto) {
        long dtoId = giftCertificateDto.getGiftId();
        Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findCertificateById(dtoId)).withSelfRel();
        Link deleteLink =
                WebMvcLinkBuilder.linkTo(controller.deleteCertificateById(dtoId)).withRel("delete gift");
        Link allLink = WebMvcLinkBuilder.linkTo(controller).withRel("gifts");
        giftCertificateDto.add(dtoLink, deleteLink, allLink);
        giftCertificateDto.getTags().forEach(tagLinkModifier::withTagLocation);
        return giftCertificateDto;
    }

    @Override
    public List<GiftCertificateDto> allWithPagination(
            List<GiftCertificateDto> dtos, RequestParametersDto dto) {
        dtos.forEach(this::withTagLocation);
        return dtos;
    }
}
