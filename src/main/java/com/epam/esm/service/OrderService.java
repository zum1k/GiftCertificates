package com.epam.esm.service;

import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;

import java.util.List;

public interface OrderService {

  OrderDto createOrder(long id, OrderDto dto);

  List<OrderDto> findUserOrders(long userId, RequestParametersDto dto);

  List<OrderDto> findAll(RequestParametersDto dto);

  OrderDto findOrderById(long userId, long orderId);
}
