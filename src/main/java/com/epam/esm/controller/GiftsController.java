package com.epam.esm.controller;

import com.epam.esm.entity.DateSortType;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.service.GiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping(value = "/certificates")
public class GiftsController {
    private final GiftService giftService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GiftCertificateDto> findAll(
            @RequestParam(required = false) String tagName,
            @RequestParam(required = false) String partName,
            @RequestParam(required = false) String partDescription,
            @RequestParam(required = false) DateSortType sortDate
    ) {
        log.info("get all certificates");
        return giftService.findAll(tagName, partName, partDescription, sortDate);
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDto addCertificate(@RequestBody @Validated GiftCertificateDto dto) {
        log.info("add certificate");
        return giftService.add(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto findCertificateById(@PathVariable("id") final long id) {
        log.info("get certificate {}", id);
        return giftService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto updateGiftCertificates(@PathVariable("id") final long id, @RequestBody @Validated GiftCertificateDto certificateDto) {
        return giftService.update(id, certificateDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCertificateById(@PathVariable("id") final long id) {
        giftService.remove(id);
    }

}
