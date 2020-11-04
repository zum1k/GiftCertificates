package com.epam.esm.controller;


import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.TagDto;
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

    @RequestMapping(value = "/hello-world", method = RequestMethod.GET, produces = "application/json")
    public String sayHello() {
        return "Hello epta!";
    }

    @ResponseBody
    @GetMapping("/nope")
    public String sayNo() {
        return "No!";
    }

    @RequestMapping(value = "/certificates", method = RequestMethod.GET)
    public List<GiftCertificateDto> getCertificates() {
        return giftService.findAll();
    }

    @RequestMapping(value = "/certificate", consumes = "application/json", method = RequestMethod.POST)
    public void addCertificate(@RequestBody GiftCertificateDto dto) {
        giftService.add(dto)
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto getCertificate(@PathVariable("id") final long id) {
        return giftService.findById(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void updateGiftCertificates(@PathVariable("id") final long id, @RequestBody GiftCertificateDto certificateDto) {
        giftService.update(certificateDto);
    }

    @RequestMapping(value = "/tag", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<GiftCertificateDto> findByTagName(@RequestBody TagDto tagDto) {
        return giftService.findByTagName(tagDto.getName());
    }
}
