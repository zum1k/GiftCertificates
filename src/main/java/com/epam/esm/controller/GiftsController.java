package com.epam.esm.controller;


import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.service.impl.GiftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ComponentScan
@RestController
public class GiftsController {

    private final GiftServiceImpl giftService;

    @Autowired
    public GiftsController(GiftServiceImpl giftService) {
        this.giftService = giftService;
    }

    @RequestMapping(value = "/certificates", method = RequestMethod.GET)
    public List<GiftCertificateDto> getCertificates() {
        return giftService.findAll();
    }

    @RequestMapping(value = "/certificates", consumes = "application/json", method = RequestMethod.POST)
    public void addCertificate(@RequestBody GiftCertificateDto dto) {
        giftService.add(dto);
    }

    @RequestMapping(value = "/certificates/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto getCertificate(@PathVariable("id") final long id) {
        return giftService.findById(id);
    }

    @RequestMapping(value = "/certificates/{id}", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void updateGiftCertificates(@PathVariable("id") final long id, @RequestBody GiftCertificateDto certificateDto) {
        giftService.update(certificateDto);
    }

    @RequestMapping(value = "/certificates?tag_name={tag_name}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<GiftCertificateDto> findByTagName(@PathVariable("tag_name") final String tagName) {
        return giftService.findByTagName(tagName);
    }

    @RequestMapping(value = "/certificates?sort=name_asc", method = RequestMethod.GET, produces = "application/json")
    public List<GiftCertificateDto> filterASC() {
        return giftService.sortByNameASC();
    }
}
