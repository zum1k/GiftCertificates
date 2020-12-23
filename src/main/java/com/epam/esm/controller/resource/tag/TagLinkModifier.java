package com.epam.esm.controller.resource.tag;

import com.epam.esm.controller.TagController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagLinkModifier implements DtoLinkModifier<TagDto> {
  private static final TagController controller = WebMvcLinkBuilder.methodOn(TagController.class);

  @Override
  public TagDto withTagLocation(TagDto tagDto) {
    long dtoId = tagDto.getId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findTagById(dtoId)).withSelfRel();
    Link deleteLink = WebMvcLinkBuilder.linkTo(controller.deleteTagById(dtoId)).withSelfRel();
    Link allLink = WebMvcLinkBuilder.linkTo(controller).withRel("gifts");
    tagDto.add(dtoLink, deleteLink, allLink);
    return tagDto;
  }

  @Override
  public List<TagDto> allWithPagination(List<TagDto> dtos, RequestParametersDto dto) {
    dtos.forEach(this::withTagLocation);
    return dtos;
  }
}
