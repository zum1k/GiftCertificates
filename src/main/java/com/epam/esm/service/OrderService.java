package com.epam.esm.service;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.dto.RequestParametersDto;

import java.util.List;

public interface OrderService {

    Order createOrder(long userId, List<Long> giftIds);

    Order removeOrder(long orderId);

    List<Order> findUserOrders(long userId, RequestParametersDto dto);

    List<Order> findAll(RequestParametersDto dto);

}
