package com.epam.esm.security;

import com.epam.esm.entity.dto.LoginUserDto;
import com.epam.esm.entity.dto.UserDto;

public interface AuthenticateService {
  String authenticate(LoginUserDto userDto);
}
