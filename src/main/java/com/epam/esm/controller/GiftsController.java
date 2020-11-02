package com.epam.esm.controller;


import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.service.impl.GiftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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

   @RequestMapping(value = "/hello-world", method = RequestMethod.GET,produces = "application/json")
    public String sayHello() {
        return "Hello epta!";
    }

    @ResponseBody
    @GetMapping("/nope")
    public String sayNo() {
        return "No!";
    }

    @RequestMapping(value = "/certificates", method = RequestMethod.GET)
    public List<GiftCertificateDto> getGifts() {
        return giftService.findAll();
    }

    @RequestMapping(value = "/gifts", consumes = "application/json", method = RequestMethod.POST)
    public void addGiftCertificate(@RequestBody GiftCertificateDto dto) {
        System.out.println(dto.toString());
//        Map<String, String[]> params = webRequest.getParameterMap();
//        GiftCertificate giftCertificate = new GiftCertificate(params.get("name"));
//        return giftService.findAll();

    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateGiftCertificates() {
        giftService.update();
    }
}
