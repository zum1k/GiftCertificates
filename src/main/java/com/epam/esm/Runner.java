package com.epam.esm;

import com.epam.esm.service.GiftService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Runner {
    private GiftService giftService;

    public static void main(String[] arguments) {

        ApplicationContext context = new AnnotationConfigApplicationContext();
    }
}
