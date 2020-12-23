package com.epam.esm.controller.resource.order;

import com.epam.esm.controller.UserController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.controller.resource.certificate.GiftCertificateLinkModifier;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderLinkModifier implements DtoLinkModifier<OrderDto> {
  private static final UserController controller = WebMvcLinkBuilder.methodOn(UserController.class);
  private final GiftCertificateLinkModifier giftCertificateLinkModifier;

  @Override
  public OrderDto withTagLocation(OrderDto dto) {
    RequestParametersDto parametersDto = new RequestParametersDto();
    long dtoId = dto.getOrderId();
    long userId = dto.getUserId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.orderById(userId, dtoId)).withSelfRel();
    Link ordersLink =
        WebMvcLinkBuilder.linkTo(controller.ordersByUserId(userId, parametersDto))
            .withRel("orders");
    Link addOrderLink =
            WebMvcLinkBuilder.linkTo(controller.addOrder(userId, dto)).withRel("add order");
    dto.add(dtoLink,addOrderLink, ordersLink);
    dto.getGifts().forEach(giftCertificateLinkModifier::withTagLocation);
    return dto;
  }

  @Override
  public List<OrderDto> allWithPagination(List<OrderDto> dtos, RequestParametersDto dto) {
    dtos.forEach(this::withTagLocation);
    return dtos;
  }
}
