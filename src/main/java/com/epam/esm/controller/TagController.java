package com.epam.esm.controller;

import com.epam.esm.controller.resource.tag.TagLinkModifier;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/tags")
public class TagController {
  private final TagLinkModifier linkModifier;
  private final TagService tagService;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<TagDto>> findAll(RequestParametersDto dto) {
    log.info("get tags");
    List<TagDto> tagDtos = tagService.findAll(dto);
    linkModifier.allWithPagination(tagDtos, dto);
    return ResponseEntity.ok().body(tagDtos);
  }

  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<TagDto> addTag(@RequestBody TagDto dto) {
    log.info("add tag");
    TagDto tagDto = tagService.addTagIfNotExist(dto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(tagDto.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<TagDto> findTagById(@PathVariable("id") final long id) {
    log.info("get tag {}", id);
    TagDto dto = tagService.findOne(id);
    linkModifier.withTagLocation(dto);
    return ResponseEntity.ok().body(dto);
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<TagDto> deleteTagById(@PathVariable("id") final long id) {
    log.info("get tag {}", id);
    TagDto tagDto = tagService.remove(id);
    return ResponseEntity.ok().body(tagDto);
  }
}
