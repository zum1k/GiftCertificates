package com.epam.esm;

import com.epam.esm.service.GiftService;
import com.epam.esm.service.impl.GiftServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.sql.SQLOutput;


public class Runner {
    private GiftService giftService;

    public static void main(String[] arguments) {
//        ApplicationContext context = new AnnotationConfigApplicationContext();
//        System.out.println(context.getBean(GiftServiceImpl.class));
    }
}
