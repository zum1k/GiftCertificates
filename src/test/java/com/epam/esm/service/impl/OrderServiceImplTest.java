package com.epam.esm.service.impl;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.certificate.CertificateRepository;
import com.epam.esm.repository.order.OrderRepository;
import com.epam.esm.repository.specification.OrdersByUserIDCriteriaSpecification;
import com.epam.esm.repository.user.UserRepository;
import com.epam.esm.service.mapper.order.OrderMapper;
import com.epam.esm.service.order.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
  @InjectMocks private OrderServiceImpl service;

  @Mock private OrderRepository repository;
  @Mock private OrderMapper mapper;
  @Mock private UserRepository userRepository;
  @Mock private CertificateRepository certificateRepository;

  //    @Test
  //    void createOrder_ShouldReturnOrder_True_Test() {
  //      TagDto tagDto = new TagDto("name1");
  //      tagDto.setId(1);
  //      List<TagDto> tagDtos = Collections.singletonList(tagDto);
  //
  //      GiftCertificateDto certificateDto =
  //          new GiftCertificateDto("name1", "description1", new BigDecimal("100.10"), 10,
  // tagDtos);
  //      List<GiftCertificateDto> certificateDtoList = Collections.singletonList(certificateDto);
  //
  //      long userId = 1;
  //      User user = new User();
  //      user.setUserId(userId);
  //
  //      OrderDto orderDto = new OrderDto();
  //      orderDto.setUserId(userId);
  //      orderDto.setCreateDate(ZonedDateTime.now().withFixedOffsetZone());
  //      orderDto.setPurchaseDate(ZonedDateTime.now().withFixedOffsetZone());
  //      orderDto.setLastUpdateDate(ZonedDateTime.now().withFixedOffsetZone());
  //      orderDto.setGifts(certificateDtoList);
  //      Order order = new Order();
  //
  //      orderDto.setUserId(userId);
  //
  //      Mockito.when(userRepository.find(userId)).thenReturn(Optional.of(user));
  //      Mockito.when(repository.add(order)).thenReturn(Optional.of(order));
  //      Mockito.when(mapper.toDto(order)).thenReturn(orderDto);
  //
  //      OrderDto resultDto = service.createOrder(userId, orderDto);
  //
  //      Assertions.assertEquals(orderDto, resultDto);
  //    }

  @Test
  void createOrder_ShouldReturn_EntityNotFoundException_Test() {}

  @Test
  void createOrder_ShouldReturn_EntityNotAddedException_Test() {}

  @Test
  void findUserOrders_ShouldReturn_Users_Test() {
    long userId = 1;
    int page = 1;
    int pageSize = 5;
    RequestParametersDto parametersDto = new RequestParametersDto();
    parametersDto.setPage(page);
    parametersDto.setPageLimit(pageSize);
    List<Order> orders = new ArrayList<>();
    List<OrderDto> orderDtos = new ArrayList<>();
    for (int i = 1; i < 6; i++) {
      User user = new User();
      user.setUserId(userId);

      Order order = new Order();
      order.setUser(user);
      order.setOrderId(i);
      order.setPrice(new BigDecimal("100" + 10 * i));
      orders.add(order);

      OrderDto orderDto = new OrderDto();
      orderDto.setOrderId(i);
      orderDto.setUserId(userId);
      orderDto.setPrice(new BigDecimal("100" + 10 * i));
      orderDtos.add(orderDto);
    }
    Mockito.when(
            repository.findAllBySpecification(
                Mockito.any(OrdersByUserIDCriteriaSpecification.class),
                Mockito.any(Integer.class),
                Mockito.any(Integer.class)))
        .thenReturn(orders);
    Mockito.when(mapper.toDtos(orders)).thenReturn(orderDtos);

    List<OrderDto> actualList = service.findUserOrders(userId, parametersDto);
    Assertions.assertEquals(orderDtos, actualList);
  }

  @Test
  void findAll() {
    RequestParametersDto parametersDto = new RequestParametersDto();
    parametersDto.setPage(1);
    parametersDto.setPageLimit(5);
    List<Order> orders = new ArrayList<>();
    List<OrderDto> orderDtos = new ArrayList<>();
    for (int i = 1; i < 6; i++) {
      User user = new User();
      user.setUserId(1);

      Order order = new Order();
      order.setUser(user);
      order.setOrderId(i);
      order.setPrice(new BigDecimal("100" + 10 * i));
      orders.add(order);

      OrderDto orderDto = new OrderDto();
      orderDto.setOrderId(i);
      orderDto.setUserId(1);
      orderDto.setPrice(new BigDecimal("100" + 10 * i));
      orderDtos.add(orderDto);
    }

    Mockito.when(repository.findAll(parametersDto.getPage(), parametersDto.getPageLimit()))
        .thenReturn(orders);
    Mockito.when(mapper.toDtos(orders)).thenReturn(orderDtos);

    List<OrderDto> actualList = service.findAll(parametersDto);
    Assertions.assertEquals(orderDtos, actualList);
  }
  @Test
  void findOrderById_ShouldReturn_Order_Test() {
    long userId = 1;
    long orderId = 1;
    Order order = new Order();
    order.setOrderId(orderId);
    order.setPrice(new BigDecimal("100"));

    User user = new User();
    user.setUserId(userId);
    order.setUser(user);

    OrderDto orderDto = new OrderDto();
    orderDto.setOrderId(orderId);
    orderDto.setUserId(userId);

    Mockito.when(repository.findOrder(orderId)).thenReturn(Optional.of(order));
    Mockito.when(mapper.toDto(order)).thenReturn(orderDto);

    OrderDto actualDto = service.findOrderById(userId, orderId);
    Assertions.assertEquals(orderDto, actualDto);


  }
  @Test
  void findOrderById_ShouldReturn_Exception_Test() {
    long userId = 1;
    long orderId = 1;
    Mockito.when(repository.findOrder(orderId)).thenReturn(Optional.empty());
    Assertions.assertThrows(
            EntityNotFoundException.class,
            () -> {
              service.findOrderById(userId, orderId);
            });
    Mockito.verify(repository).findOrder(Mockito.eq(orderId));
  }
}
