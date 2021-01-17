package com.epam.esm.controller.resource.order;

import com.epam.esm.controller.UserController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.controller.resource.certificate.GiftCertificateLinkModifier;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.service.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderLinkModifier implements DtoLinkModifier<OrderDto> {
  private static final int FIRST_PAGE = 1;
  private static final UserController controller = WebMvcLinkBuilder.methodOn(UserController.class);
  private final GiftCertificateLinkModifier giftCertificateLinkModifier;
  private final OrderService service;

  @Override
  public void withTagLocation(OrderDto dto) {
    RequestParametersDto parametersDto = new RequestParametersDto();
    long dtoId = dto.getOrderId();
    long userId = dto.getUserId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.orderById(userId, dtoId)).withSelfRel();
    Link ordersLink =
        WebMvcLinkBuilder.linkTo(controller.ordersByUserId(userId, parametersDto.getPage(), parametersDto.getPageLimit()))
            .withRel("orders");
    Link addOrderLink =
        WebMvcLinkBuilder.linkTo(controller.addOrder(userId, dto)).withRel("add order");
    dto.add(dtoLink, addOrderLink, ordersLink);
    dto.getGifts().forEach(giftCertificateLinkModifier::withTagLocation);
  }

  @Override
  public CollectionModel<OrderDto> allWithPagination(List<OrderDto> dtos, RequestParametersDto dto) {
    CollectionModel<OrderDto> model = CollectionModel.of(dtos);

    int page = dto.getPage();
    int pageAmount = (int) service.count(dto);
    if (pageAmount != 0) {
      dto.setPage(FIRST_PAGE);
      Link firstPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
          ordersByUserId(dtos.get(FIRST_PAGE).getOrderId(), dto.getPage(), dto.getPageLimit())).withRel("first");
      model.add(firstPage.expand());

      dto.setPage(pageAmount);
      Link lastPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
          ordersByUserId(dtos.get(FIRST_PAGE).getOrderId(), dto.getPage(), dto.getPageLimit())).withRel("first");
      model.add(lastPage.expand());

      if (dto.getPage() != 1) {
        dto.setPage(dto.getPage() - 1);
        Link prevPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
            ordersByUserId(dtos.get(FIRST_PAGE).getOrderId(), dto.getPage(), dto.getPageLimit())).withRel("first");
        model.add(prevPage.expand());
      }

      if (page != pageAmount) {
        dto.setPage(page + 1);
        Link nextPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).
            ordersByUserId(dtos.get(FIRST_PAGE).getOrderId(), dto.getPage(), dto.getPageLimit())).withRel("first");
        model.add(nextPage.expand());
      }
    }
    model.forEach(this::withTagLocation);
    return model;
  }
}
