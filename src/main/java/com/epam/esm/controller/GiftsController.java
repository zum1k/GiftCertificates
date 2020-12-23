package com.epam.esm.controller;

import com.epam.esm.controller.resource.certificate.GiftCertificateLinkModifier;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.service.certificate.GiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/certificates")
public class GiftsController {
  private final GiftCertificateLinkModifier linkModifier;
  private final GiftService giftService;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<GiftCertificateDto>> findAll(RequestParametersDto dto) {
    log.info("find all certificates");
    List<GiftCertificateDto> resultList = giftService.findAll(dto);
    linkModifier.allWithPagination(resultList, dto);
    return ResponseEntity.ok().body(resultList);
  }

  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<GiftCertificateDto> addCertificate(
      @Validated @RequestBody GiftCertificateDto dto) {
    log.info("add certificate");
    GiftCertificateDto giftCertificateDto = giftService.add(dto);
    long dtoId = giftCertificateDto.getGiftId();
    URI resourceUri =
        ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + dtoId).build().toUri();
    return ResponseEntity.created(resourceUri).build();
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<GiftCertificateDto> findCertificateById(@PathVariable("id") final long id) {
    log.info("get certificate {}", id);
    GiftCertificateDto dto = giftService.findById(id);
    linkModifier.withTagLocation(dto);
    return ResponseEntity.ok().body(dto);
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<GiftCertificateDto> updateGiftCertificates(
      @PathVariable("id") final long id,
      @RequestBody @Validated(GiftCertificateDto.class) GiftCertificateDto certificateDto) {
    log.info("update gift {}", id);
    GiftCertificateDto dto = giftService.update(id, certificateDto);
    linkModifier.withTagLocation(dto);
    return ResponseEntity.ok().body(dto);
  }

  @RequestMapping(
      value = "/{id}",
      method = RequestMethod.DELETE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GiftCertificateDto> deleteCertificateById(
      @PathVariable("id") final long id) {
    log.info("remove gift by {}", id);
    GiftCertificateDto dto = giftService.remove(id);
    return ResponseEntity.ok().body(dto);
  }
}
