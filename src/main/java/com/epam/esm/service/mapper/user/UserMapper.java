package com.epam.esm.service.mapper.user;

import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.UserDto;
import com.epam.esm.service.mapper.order.OrderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {OrderMapper.class})
public interface UserMapper {
  @Mapping(target = "orders", ignore = true)
  User toEntity(UserDto dto);

  UserDto toDto(User user);

  List<UserDto> toDtoList(List<User> userList);
}
