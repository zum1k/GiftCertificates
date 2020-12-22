package com.epam.esm.controller;

import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.service.GiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/certificates")
public class GiftsController { // TODO
  private final GiftService giftService;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<GiftCertificateDto> findAll(RequestParametersDto dto) {
    log.info("find all certificates");
    return giftService.findAll(dto);
  }

  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public GiftCertificateDto addCertificate(@Validated @RequestBody GiftCertificateDto dto) {
    log.info("add certificate");
    return giftService.add(dto);
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<GiftCertificateDto> findCertificateById(@PathVariable("id") final long id) {
    log.info("get certificate {}", id);
    GiftCertificateDto dto = giftService.findById(id);
    addLinks(dto);
    return ResponseEntity.ok().body(dto);
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public GiftCertificateDto updateGiftCertificates(
      @PathVariable("id") final long id,
      @RequestBody @Validated(GiftCertificateDto.class) GiftCertificateDto certificateDto) {
    return giftService.update(id, certificateDto);
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.DELETE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteCertificateById(@PathVariable("id") final long id) {
    giftService.remove(id);
  }

  private void addLinks(GiftCertificateDto certificateDto) {
    certificateDto
        .getTags()
        .forEach(
            tagDto -> {
              long tagId = tagDto.getId();
              Link tagLink =
                  WebMvcLinkBuilder.linkTo(
                          WebMvcLinkBuilder.methodOn(TagController.class).findTagById(tagId))
                      .withRel("tag");
              tagDto.add(tagLink);
            });
  }
}
