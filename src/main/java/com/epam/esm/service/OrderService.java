package com.epam.esm.service;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(long id, OrderDto dto);

    OrderDto removeOrder(long orderId);

    List<OrderDto> findUserOrders(long userId, RequestParametersDto dto);

    List<OrderDto> findAll(RequestParametersDto dto);
}
