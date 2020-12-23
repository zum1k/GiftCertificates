package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.OrderDto;
import com.epam.esm.entity.dto.RequestParametersDto;

import java.util.List;

public interface UserService {
    User findUser(long userId);

    List<User> findAll(RequestParametersDto dto);

    Tag findWidelyUsedTagByAllOrdersCost(long userId);
}
