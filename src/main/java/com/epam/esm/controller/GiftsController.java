package com.epam.esm.controller;


import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.service.impl.GiftServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ComponentScan
@RestController
@RequestMapping(value = "/certificates")
public class GiftsController {
    private final GiftServiceImpl giftService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<GiftCertificateDto> getCertificates() {
        log.info("get all certificates");
        return giftService.findAll();
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCertificate(@RequestBody GiftCertificateDto dto) {
        log.info("add certificate");
        giftService.add(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto getCertificate(@PathVariable("id") final long id) {
        log.info("get certificate {}", id);
        return giftService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void updateGiftCertificates(@PathVariable("id") final long id, @RequestBody GiftCertificateDto certificateDto) {
        giftService.update(certificateDto);
    }

    @RequestMapping(value = "?tag_name={tag_name}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<GiftCertificateDto> findByTagName(@PathVariable("tag_name") final String tagName) {
        return giftService.findByTagName(tagName);
    }

    @RequestMapping(value = "?sort=name_asc", method = RequestMethod.GET, produces = "application/json")
    public List<GiftCertificateDto> filterASC() {
        return giftService.sortByNameASC();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json")
    public void deleteCertificateById(@PathVariable("id") final long id) {
        giftService.remove(id);
    }

    @RequestMapping(value = "?name={part_name}", method = RequestMethod.GET, produces = "application/json")
    public List<GiftCertificateDto> findByPartName(@PathVariable("part_name") final String partName) {
        return giftService.findByPartName(partName);
    }
}
