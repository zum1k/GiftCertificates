package com.epam.esm.controller.resource.user;

import com.epam.esm.controller.UserController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.controller.resource.order.OrderLinkModifier;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.UserDto;
import com.epam.esm.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserLinkModifier implements DtoLinkModifier<UserDto> {
  private static final int FIRST_PAGE = 1;
  private static final UserController controller = WebMvcLinkBuilder.methodOn(UserController.class);
  private final UserService service;
  private final OrderLinkModifier orderLinkModifier;

  @Override
  public void withTagLocation(UserDto userDto) {
    RequestParametersDto dto = new RequestParametersDto();
    long userId = userDto.getUserId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findUser(userId)).withSelfRel();
    Link ordersLink =
        WebMvcLinkBuilder.linkTo(controller.ordersByUserId(userId, dto.getPage(), dto.getPageLimit())).withRel("orders");
    Link userTagLink =
        WebMvcLinkBuilder.linkTo(
            controller.getTheMostWidelyUsedTagOfUserWithTheHighestCostOfAllOrders(userId))
            .withRel("most_used_tag");
    userDto.add(dtoLink, ordersLink, userTagLink);
    userDto.getOrders().forEach(orderLinkModifier::withTagLocation);
  }

  @Override
  public CollectionModel<UserDto> allWithPagination(List<UserDto> users, RequestParametersDto dto) {
    CollectionModel<UserDto> model = CollectionModel.of(users);
    int page = dto.getPage();
    int pageAmount = (int) service.count(dto);
    if (pageAmount != 0) {
      dto.setPage(FIRST_PAGE);
      Link firstPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
          findAll(dto.getPage(), dto.getPageLimit())).withRel("first");
      model.add(firstPage.expand());

      dto.setPage(pageAmount);
      Link lastPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
          findAll(dto.getPage(), dto.getPageLimit())).withRel("last");
      model.add(lastPage.expand());

      if (dto.getPage() != 1) {
        dto.setPage(dto.getPage() - 1);
        Link prevPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
            findAll(dto.getPage(), dto.getPageLimit()))
            .withRel("prev");
        model.add(prevPage.expand());
      }

      if (page != pageAmount) {
        dto.setPage(page + 1);
        Link nextPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
            findAll(dto.getPage(), dto.getPageLimit()))
            .withRel("next");
        model.add(nextPage.expand());
      }
    }
    model.forEach(this::withTagLocation);
    return model;
  }
}
