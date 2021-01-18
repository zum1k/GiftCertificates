package com.epam.esm.service.order;

import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;

import java.util.List;

public interface OrderService {

  OrderDto createOrder(long id, OrderDto dto);

  List<OrderDto> findUserOrders(long userId, RequestParametersDto dto);

  /**
   * Find all orderDto's by specific query.
   *
   * @param dto {@code RequestParametersDto} with query values.
   * @return List of {@code OrderDto} satisfying to parameters.
   */
  List<OrderDto> findAll(RequestParametersDto dto);

  OrderDto findOrderById(long userId, long orderId);

  /**
   * Count orders amount by specific query.
   *
   * @param dto {@code RequestParametersDto}'s with query values.
   * @return the number of pages depending on the request parameters
   */
  long count(long userId, RequestParametersDto dto);
}
