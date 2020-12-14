package com.epam.esm.repository.order;

import com.epam.esm.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> add(Order order);

    Optional<Order> remove(long orderId);

    Optional<Order> findOrder(long id);

    List<Order> findAll(int page, int pageSize);

}
