package com.epam.esm.controller.resource.user;

import com.epam.esm.controller.UserController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.controller.resource.order.OrderLinkModifier;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.RequestParametersDto;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserLinkModifier implements DtoLinkModifier<User> {
  private static final UserController controller = WebMvcLinkBuilder.methodOn(UserController.class);
  private final OrderLinkModifier orderLinkModifier;

  @Override
  public User withTagLocation(User user) {
    RequestParametersDto dto = new RequestParametersDto();
    long userId = user.getUserId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findUser(userId)).withSelfRel();
    Link ordersLink =
        WebMvcLinkBuilder.linkTo(controller.ordersByUserId(userId, dto)).withRel("orders");
    Link userTagLink =
        WebMvcLinkBuilder.linkTo(
                controller.getTheMostWidelyUsedTagOfUserWithTheHighestCostOfAllOrders(userId))
            .withRel("most used tag");
    user.add(dtoLink, ordersLink, userTagLink);
    user.getOrders().forEach(orderLinkModifier::withTagLocation);
    return user;
  }

  @Override
  public List<User> allWithPagination(List<User> users, RequestParametersDto dto) {
    users.forEach(this::withTagLocation);
    return users;
  }
}
