package com.epam.esm.controller.resource.certificate;

import com.epam.esm.controller.GiftsController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.controller.resource.tag.TagLinkModifier;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.service.certificate.GiftService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GiftCertificateLinkModifier implements DtoLinkModifier<GiftCertificateDto> {
  private static final int FIRST_PAGE = 1;
  private static final GiftsController controller =
      WebMvcLinkBuilder.methodOn(GiftsController.class);
  private final TagLinkModifier tagLinkModifier;
  private final GiftService service;

  @Override
  public void withTagLocation(GiftCertificateDto giftCertificateDto) {
    long dtoId = giftCertificateDto.getGiftId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findCertificateById(dtoId)).withSelfRel();
    Link deleteLink =
        WebMvcLinkBuilder.linkTo(controller.deleteCertificateById(dtoId)).withRel("delete gift");
    giftCertificateDto.add(dtoLink, deleteLink);
    giftCertificateDto.getTags().forEach(tagLinkModifier::withTagLocation);
  }

  @Override
  public CollectionModel<GiftCertificateDto> allWithPagination(
      List<GiftCertificateDto> dtos, RequestParametersDto dto) {
    CollectionModel<GiftCertificateDto> model = CollectionModel.of(dtos);
    int page = dto.getPage();
    int pageAmount = (int) service.count(dto);
    if (pageAmount != 0) {
      dto.setPage(FIRST_PAGE);
      Link firstPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftsController.class).
          findAll(dto.getPage(), dto.getPageLimit(),
              dto.getTagName(), dto.getPartName(),
              dto.getPartDescription(), dto.getType())).withRel("first");
      model.add(firstPage.expand());

      dto.setPage(pageAmount);
      Link lastPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftsController.class).
          findAll(dto.getPage(), dto.getPageLimit(),
              dto.getTagName(), dto.getPartName(),
              dto.getPartDescription(), dto.getType())).withRel("last");
      model.add(lastPage.expand());

      if (dto.getPage() != 1) {
        dto.setPage(dto.getPage() - 1);
        Link prevPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftsController.class).
            findAll(dto.getPage(), dto.getPageLimit(),
                dto.getTagName(), dto.getPartName(),
                dto.getPartDescription(), dto.getType()))
            .withRel("prev");
        model.add(prevPage.expand());
      }

      if (page != pageAmount) {
        dto.setPage(page + 1);
        Link nextPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftsController.class).
            findAll(dto.getPage(), dto.getPageLimit(),
                dto.getTagName(), dto.getPartName(),
                dto.getPartDescription(), dto.getType()))
            .withRel("next");
        model.add(nextPage.expand());
      }
    }
    model.forEach(this::withTagLocation);
    return model;
  }
}
