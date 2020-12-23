package com.epam.esm.controller.resource.user;

import com.epam.esm.controller.UserController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.controller.resource.order.OrderLinkModifier;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserLinkModifier implements DtoLinkModifier<UserDto> {
  private static final UserController controller = WebMvcLinkBuilder.methodOn(UserController.class);
  private final OrderLinkModifier orderLinkModifier;

  @Override
  public UserDto withTagLocation(UserDto userDto) {
    RequestParametersDto dto = new RequestParametersDto();
    long userId = userDto.getUserId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findUser(userId)).withSelfRel();
    Link ordersLink =
        WebMvcLinkBuilder.linkTo(controller.ordersByUserId(userId, dto)).withRel("orders");
    Link userTagLink =
        WebMvcLinkBuilder.linkTo(
                controller.getTheMostWidelyUsedTagOfUserWithTheHighestCostOfAllOrders(userId))
            .withRel("most used tag");
    userDto.add(dtoLink, ordersLink, userTagLink);
    userDto.getOrders().forEach(orderLinkModifier::withTagLocation);
    return userDto;
  }

  @Override
  public List<UserDto> allWithPagination(List<UserDto> users, RequestParametersDto dto) {
    users.forEach(this::withTagLocation);
    return users;
  }
}
