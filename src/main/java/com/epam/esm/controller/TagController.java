package com.epam.esm.controller;

import com.epam.esm.controller.resource.tag.TagLinkModifier;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagController {
  private final TagLinkModifier linkModifier;
  private final TagService tagService;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CollectionModel<TagDto>> findAll(@RequestParam(required = false, defaultValue = "1")
                                                         @Min(value = 1, message = "page must be positive") Integer page,
                                                         @Min(value = 1, message = "page should be positive")
                                                         @Max(value = 100, message = "page size must not be greater than 100")
                                                         @RequestParam(required = false, defaultValue = "50") Integer pageSize

  ) {
    log.info("get tags");
    RequestParametersDto dto = new RequestParametersDto();
    dto.setPage(page);
    dto.setPageLimit(pageSize);

    List<TagDto> tagDtos = tagService.findAll(dto);
    return ResponseEntity.ok().body(linkModifier.allWithPagination(tagDtos, dto));
  }

  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<TagDto> addTag(@Valid @RequestBody TagDto dto) {
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
  public ResponseEntity<TagDto> findTagById(@PathVariable("id")
                                            @Min(value = 1, message = "id must be positive") final long id) {
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
  public ResponseEntity<TagDto> deleteTagById(@PathVariable("id")
                                              @Min(value = 1, message = "id must be positive") final long id) {
    log.info("get tag {}", id);
    tagService.remove(id);
    return ResponseEntity.noContent().build();
  }
}
