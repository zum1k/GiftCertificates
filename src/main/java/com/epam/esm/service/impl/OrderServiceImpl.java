package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.repository.order.OrderRepository;
import com.epam.esm.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Override
    public Order createOrder(long userId, List<Long> giftIds) {
        log.info("add order");
        return null;
    }

    @Override
    public Order removeOrder(long orderId) {
        log.info("remove order by id {}", orderId);
        return repository.remove(orderId).orElseThrow(() -> new EntityNotDeletedException("not removed", orderId));
    }

    @Override
    public List<Order> findUserOrders(long userId, RequestParametersDto dto) {
        log.info("find user {} orders ", userId);
        return null;
    }

    @Override
    public List<Order> findAll(RequestParametersDto dto) {
        log.info("find orders");
        return repository.findAll(dto.getPage(), dto.getPageLimit());
    }

    private BigDecimal calculateOrderCost(List<GiftCertificate> gifts) {
        List<BigDecimal> costs = gifts.stream().map(GiftCertificate::getPrice).collect(Collectors.toList());
        return costs.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
