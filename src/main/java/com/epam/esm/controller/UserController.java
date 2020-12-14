package com.epam.esm.controller;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private OrderService orderService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public User findUser(@PathVariable("id") final long id) {
        log.info("find user {}", id);
        return userService.findUser(id);
    }
    @RequestMapping(value = "/{id}/orders",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Order addOrder(long id, List<Long> giftIds) {
        log.info("add order {}", id);
        return orderService.createOrder(id, giftIds);
    }
    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll(RequestParametersDto dto) {
        log.info("find all users");
        return userService.findAll(dto);
    }
    @RequestMapping(value = "/{id}/tag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Tag getTheMostWidelyUsedTagOfUserWithTheHighestCostOfAllOrders(@PathVariable("id")long id) {
        log.info("find most widely used tag");
        return userService.findWidelyUsedTagByAllOrdersCost(id);
    }
}
