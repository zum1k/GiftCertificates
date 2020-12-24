package com.epam.esm.service.mapper.order;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.service.mapper.certificate.CertificateMapper;
import com.epam.esm.service.mapper.tag.TagMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {CertificateMapper.class, TagMapper.class})
public interface OrderMapper {
  @Mappings({@Mapping(target = "gifts", ignore = true), @Mapping(target = "user", ignore = true)})
  Order toEntity(OrderDto dto);

  @Mapping(target = "userId", source = "order.user.userId")
  OrderDto toDto(Order order);

  List<OrderDto> toDtos(List<Order> orders);
}
