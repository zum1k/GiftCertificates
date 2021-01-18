package com.epam.esm.repository.order;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.repository.CriteriaSpecification;
import com.epam.esm.repository.specification.OrdersByUserIDCriteriaSpecification;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class OrderRepositoryImplTest {
  private final OrderRepository repository;

  @Test
  @Transactional
  void add_ShouldReturn_Order_True() {
    long expectedUserId = 4;
    User user = new User();
    user.setUserId(expectedUserId);
    Set<GiftCertificate> certificates = new HashSet<>();

    Order order = new Order();
    order.setPurchaseDate(ZonedDateTime.now());
    order.setUser(user);
    order.setGifts(certificates);
    order.setPrice(new BigDecimal("100"));

    long actualUserUd = repository.add(order).get().getUser().getUserId();
    assertEquals(expectedUserId, actualUserUd);
  }

  @Test
  @Transactional
  void remove_ShouldReturn_Eight_True() {
    long expectedId = 6;

    long actualId = repository.remove(expectedId).get().getOrderId();
    assertEquals(expectedId, actualId);
  }

  @Test
  @Transactional
  void findOrder_ShouldReturn_Six_True() {
    long expectedId = 6;

    long actualId = repository.findOrder(expectedId).get().getOrderId();
    assertEquals(expectedId, actualId);
  }

  @Test
  @Transactional
  void findAll_ShouldReturn_Three_True() {
    int expectedSize = 5;
    int page = 1;
    int pageSize = 5;

    int actualSize = repository.findAll(page, pageSize).size();
    assertEquals(expectedSize, actualSize);
  }

  @Test
  @Transactional
  void findAllBySpecification_ShouldReturn_Three_True() {
    long userId = 4;
    int page = 1;
    int pageSize = 5;
    int expectedSize = 4;
    CriteriaSpecification<Order> specification = new OrdersByUserIDCriteriaSpecification(userId);

    int actualSize = repository.findAllBySpecification(specification, page, pageSize).size();
    assertEquals(expectedSize, actualSize);
  }

  @Test
  @Transactional
  void count_ShouldReturn_Six_Test() {
    long expectedSize = 4;
    long userId = 1;
    CriteriaSpecification<Order> specification = new OrdersByUserIDCriteriaSpecification(userId);
    long actualSize = repository.count(specification);
    assertEquals(expectedSize, actualSize);
  }
}
