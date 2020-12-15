package com.epam.esm.service.impl;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.repository.CriteriaSpecification;
import com.epam.esm.repository.order.OrderRepository;
import com.epam.esm.repository.specifications.OrdersByUserIDCriteriaSpecification;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.mapper.order.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private static final String ORDER = "Order";
    private final OrderRepository repository;
    private final OrderMapper mapper;

    @Override
    public OrderDto createOrder(OrderDto dto) {
        log.info("add order");
        Order order = mapper.toEntity(dto);
        order.setPrice(calculateOrderCost(dto.getDtos()));
        order.setPurchaseDate(ZonedDateTime.now().withFixedOffsetZone());
        return mapper.toDto(repository.add(order).orElseThrow(() -> new EntityNotAddedException(ORDER)));
    }

    @Override
    public OrderDto removeOrder(long orderId) {
        log.info("remove order by id {}", orderId);
        return mapper.toDto(repository.remove(orderId).orElseThrow(() -> new EntityNotDeletedException(ORDER, orderId)));
    }

    @Override
    public List<OrderDto> findUserOrders(long userId, RequestParametersDto dto) {
        log.info("find user {} orders ", userId);
        CriteriaSpecification<Order> specification = new OrdersByUserIDCriteriaSpecification(userId);
        return mapper.toDtos(repository.findAllBySpecification(specification, dto.getPage(), dto.getPageLimit()));
    }

    @Override
    public List<OrderDto> findAll(RequestParametersDto dto) {
        log.info("find orders");
        return mapper.toDtos(repository.findAll(dto.getPage(), dto.getPageLimit()));
    }

    private BigDecimal calculateOrderCost(List<GiftCertificateDto> gifts) {
        List<BigDecimal> costs = gifts.stream().map(GiftCertificateDto::getPrice).collect(Collectors.toList());
        return costs.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
