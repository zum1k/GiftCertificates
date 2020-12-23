package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.dto.GiftCertificateDto;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.UserDto;
import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.CriteriaSpecification;
import com.epam.esm.repository.order.OrderRepository;
import com.epam.esm.repository.specification.OrdersByUserIDCriteriaSpecification;
import com.epam.esm.service.GiftService;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import com.epam.esm.service.mapper.certificate.CertificateMapper;
import com.epam.esm.service.mapper.order.OrderMapper;
import com.epam.esm.service.mapper.user.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
  private static final String ORDER = "Order";
  private final OrderRepository repository;
  private final OrderMapper mapper;
  private final GiftService giftService;
  private final CertificateMapper certificateMapper;
  private final UserService userService;
  private final UserMapper userMapper;

  @Transactional
  @Override
  public OrderDto createOrder(long id, OrderDto dto) {
    log.info("add order");
    dto.setUserId(id);
    Order order = mapper.toEntity(dto);
    order.setPrice(calculateOrderCost(dto.getGifts()));
    order.setPurchaseDate(ZonedDateTime.now().withFixedOffsetZone());
    Set<GiftCertificate> certificates = new HashSet<>();
    for (GiftCertificateDto gift : dto.getGifts()) {
      certificates.add(certificateMapper.toEntity(giftService.findById(gift.getGiftId())));
    }
    order.setGifts(certificates);
    UserDto user = userService.findUser(id);
    order.setUser(userMapper.toEntity(user));
    return mapper.toDto(
        repository.add(order).orElseThrow(() -> new EntityNotAddedException(ORDER)));
  }

  @Override
  public List<OrderDto> findUserOrders(long userId, RequestParametersDto dto) {
    log.info("find user {} orders ", userId);
    CriteriaSpecification<Order> specification = new OrdersByUserIDCriteriaSpecification(userId);
    List<Order> orders =
        repository.findAllBySpecification(specification, dto.getPage(), dto.getPageLimit());
    return mapper.toDtos(orders);
  }

  @Override
  public List<OrderDto> findAll(RequestParametersDto dto) {
    log.info("find orders");
    return mapper.toDtos(repository.findAll(dto.getPage(), dto.getPageLimit()));
  }

  @Override
  public OrderDto findOrderById(long userId, long orderId) {
    log.info("find user {} order by {}", userId, orderId);
    Optional<Order> optionalOrder = repository.findOrder(orderId);
    if (optionalOrder.isEmpty()) {
      throw new EntityNotFoundException(ORDER, orderId);
    }
    return null;
  }

  private BigDecimal calculateOrderCost(List<GiftCertificateDto> gifts) {
    List<BigDecimal> costs =
        gifts.stream().map(GiftCertificateDto::getPrice).collect(Collectors.toList());
    return costs.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
