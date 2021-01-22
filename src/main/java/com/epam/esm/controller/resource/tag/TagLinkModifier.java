package com.epam.esm.controller.resource.tag;

import com.epam.esm.controller.TagController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagLinkModifier implements DtoLinkModifier<TagDto> {
  private static final int FIRST_PAGE = 1;
  private static final TagController controller = WebMvcLinkBuilder.methodOn(TagController.class);
  private final TagService service;

  @Override
  public void withTagLocation(TagDto tagDto) {
    long dtoId = tagDto.getId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findTagById(dtoId)).withSelfRel();
    Link deleteLink = WebMvcLinkBuilder.linkTo(controller.deleteTagById(dtoId)).withRel("delete_tag");
    tagDto.add(dtoLink, deleteLink);
  }

  @Override
  public CollectionModel<TagDto> allWithPagination(List<TagDto> dtos, RequestParametersDto dto) {
    CollectionModel<TagDto> model = CollectionModel.of(dtos);
    int page = dto.getPage();
    int pageAmount = (int) service.count(dto);
    if (pageAmount != 0) {
      dto.setPage(FIRST_PAGE);
      Link firstPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).
          findAll(dto.getPage(), dto.getPageLimit())).withRel("first");
      model.add(firstPage.expand());

      dto.setPage(pageAmount);
      Link lastPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).
          findAll(dto.getPage(), dto.getPageLimit())).withRel("last");
      model.add(lastPage.expand());

      if (dto.getPage() != 1) {
        dto.setPage(dto.getPage() - 1);
        Link prevPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).
            findAll(dto.getPage(), dto.getPageLimit()))
            .withRel("prev");
        model.add(prevPage.expand());
      }

      if (page != pageAmount) {
        dto.setPage(page + 1);
        Link nextPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).
            findAll(dto.getPage(), dto.getPageLimit()))
            .withRel("next");
        model.add(nextPage.expand());
      }
    }
//    dtos.forEach(this::withTagLocation);
//    return model;
    model.forEach(this::withTagLocation);
    return model;
  }
}
