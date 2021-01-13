package com.epam.esm.controller;

import com.epam.esm.controller.resource.order.OrderLinkModifier;
import com.epam.esm.controller.resource.user.UserLinkModifier;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.entity.dto.UserDto;
import com.epam.esm.service.order.OrderService;
import com.epam.esm.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserLinkModifier linkModifier;
    private final OrderLinkModifier orderLinkModifier;
    private final UserService userService;
    private final OrderService orderService;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> findUser(@PathVariable("id") final long id) {
        log.info("find user {}", id);
        UserDto userDto = userService.findUser(id);
        linkModifier.withTagLocation(userDto);
        return ResponseEntity.ok().body(userDto);
    }

    @RequestMapping(
            value = "/{id}/orders",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> addOrder(
            @PathVariable("id") final long id, @Validated @RequestBody OrderDto dto) {
        log.info("add order {}", id);
        OrderDto orderDto = orderService.createOrder(id, dto);
        long dtoId = orderDto.getOrderId();
        URI resourceUri =
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + dtoId).build().toUri();
        return ResponseEntity.created(resourceUri).build();
    }

    @RequestMapping(
            value = "/{user_id}/orders/{order_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<OrderDto> orderById(
            @PathVariable("user_id") final long userId, @PathVariable("order_id") final long orderId) {
        log.info("find user {} order by {}", userId, orderId);
        OrderDto orderDto = orderService.findOrderById(userId, orderId);
        orderLinkModifier.withTagLocation(orderDto);
        return ResponseEntity.ok().body(orderDto);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDto>> findAll(RequestParametersDto dto) {
        log.info("find all users");
        List<UserDto> userDtos = userService.findAll(dto);
        linkModifier.allWithPagination(userDtos, dto);
        return ResponseEntity.ok().body(userDtos);
    }

    @RequestMapping(
            value = "/{id}/orders",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderDto>> ordersByUserId(
            @PathVariable("id") final long id, RequestParametersDto dto) {
        log.info("find all orders by user id {}", id);
        List<OrderDto> orderDtos = orderService.findUserOrders(id, dto);
        orderLinkModifier.allWithPagination(orderDtos, dto);
        return ResponseEntity.ok().body(orderDtos);
    }

    @RequestMapping(
            value = "/{id}/tag",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TagDto getTheMostWidelyUsedTagOfUserWithTheHighestCostOfAllOrders(
            @PathVariable("id") long id) {
        log.info("find most widely used tag");
        return userService.findWidelyUsedTagByAllOrdersCost(id);
    }
}
