package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class GiftsController {
    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private GiftService giftService;

    @Autowired
    public GiftsController(GiftService giftService) {
        this.giftService = giftService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GiftCertificate> getGifts() {
        return giftService.findAll();
    }
    @RequestMapping(method = RequestMethod.POST)
    public void addGiftCertificate(){
        //giftService.add()
    }
}
