package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.entity.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findUser(long userId);

    List<UserDto> findAll(RequestParametersDto dto);

    TagDto findWidelyUsedTagByAllOrdersCost(long userId);
}
