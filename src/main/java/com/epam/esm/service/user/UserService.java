package com.epam.esm.service.user;

import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.entity.dto.UserDto;

import java.util.List;

public interface UserService {
  UserDto findUser(long userId);

  List<UserDto> findAll(RequestParametersDto dto);

  TagDto findWidelyUsedTagByAllOrdersCost(long userId);
}
