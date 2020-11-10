package com.epam.esm.controller;


import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.service.GiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping(value = "/certificates") //TODO validation, optional parameters in request, bad request
public class GiftsController {
    private final GiftService giftService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<GiftCertificateDto> findAll() {
        log.info("get all certificates");
        return giftService.findAll();
    }
   //TODO
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDto addCertificate(@RequestBody GiftCertificateDto dto) {
        log.info("add certificate");
        return  giftService.add(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto findCertificateById(@PathVariable("id") final long id) {
        log.info("get certificate {}", id);
        return giftService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto updateGiftCertificates(@PathVariable("id") final long id, @RequestBody GiftCertificateDto certificateDto) {
       return giftService.update(id, certificateDto);
    }
    //TODO need to union in "getAll.method"
    @RequestMapping(value = "?tag_name={tag_name}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<GiftCertificateDto> findByTagName(@PathVariable("tag_name") final String tagName) {
        return giftService.findByTagName(tagName);
    }
    //TODO in request param
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
