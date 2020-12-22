package com.epam.esm.controller.resource.order;

import com.epam.esm.controller.TagController;
import com.epam.esm.controller.UserController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.entity.dto.OrderDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderLinkModifier implements DtoLinkModifier<OrderDto> {
    private static final UserController controller = WebMvcLinkBuilder.methodOn(UserController.class);
    @Override
    public OrderDto withTagLocation(OrderDto dto) {
        Link self = WebMvcLinkBuilder.linkTo(controller.(dto.getIdentifier()))
                .withSelfRel();
        dto.add(self);
        return dto;
    }

    @Override
    public ResponseEntity<List<OrderDto>> allWithPagination(List<OrderDto> dtos, Integer page, Integer pageSize) {
        return null;
    }
}
