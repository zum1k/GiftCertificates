package com.epam.esm.controller;

import com.epam.esm.controller.resource.certificate.GiftCertificateLinkModifier;
import com.epam.esm.entity.DateSortType;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.service.certificate.GiftService;
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
@RestController
@Validated
@RequestMapping("/certificates")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GiftsController {
  private final GiftCertificateLinkModifier linkModifier;
  private final GiftService giftService;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CollectionModel<GiftCertificateDto>> findAll(@RequestParam(name = "page", required = false, defaultValue = "1")
                                                                     @Min(value = 1, message = "page must be positive") Integer page,
                                                                     @Min(value = 1, message = "pageSize must be positive")
                                                                     @Max(value = 100, message = "pageSize size must not be greater than 100")
                                                                     @RequestParam(name = "pageSize", required = false, defaultValue = "50") Integer pageSize,
                                                                     @RequestParam(required = false) String tagName,
                                                                     @RequestParam(required = false) String partName,
                                                                     @RequestParam(required = false) String partDescription,
                                                                     @RequestParam(required = false) DateSortType sortDate) {
    log.info("find all certificates");
    RequestParametersDto dto = new RequestParametersDto();
    dto.setPage(page);
    dto.setPageLimit(pageSize);
    dto.setTagName(tagName);
    dto.setPartName(partName);
    dto.setPartDescription(partDescription);
    dto.setType(sortDate);

    List<GiftCertificateDto> resultList = giftService.findAll(dto);
    return ResponseEntity.ok().body(linkModifier.allWithPagination(resultList, dto));
  }

  @RequestMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<GiftCertificateDto> addCertificate(
      @Valid @RequestBody GiftCertificateDto dto) {
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
  public ResponseEntity<GiftCertificateDto> findCertificateById(@PathVariable("id")
                                                                @Min(value = 1, message = "id must be positive") final long id) {
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
  public ResponseEntity<GiftCertificateDto> updateGiftCertificates(@PathVariable("id")
                                                                   @Min(value = 1, message = "id must be positive") final long id,
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
  public ResponseEntity<GiftCertificateDto> deleteCertificateById(@PathVariable("id")
                                                                  @Min(value = 1, message = "id must be positive")final long id) {
    log.info("remove gift by {}", id);
    giftService.remove(id);
    return ResponseEntity.noContent().build();
  }
}
