package com.epam.esm.controller;

import com.epam.esm.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GiftsController {
    private static final String MAX_LONG_AS_STRING="9223372036854775807";

    private GiftService giftService;

    @Autowired
    public GiftsController(GiftService giftService) {
       this.giftService = giftService;
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<Spittle> spittles(
            @RequestParam(value="max",
                    defaultValue=MAX_LONG_AS_STRING) long max,

            @RequestParam(value="count", defaultValue="20") int count) {

        return spittleRepository.findSpittles(max, count);
    }
}
