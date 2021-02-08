package com.epam.esm.security;

import com.epam.esm.entity.dto.LoginUserDto;
import com.epam.esm.entity.dto.UserDto;
import com.epam.esm.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceImpl implements AuthenticateService {
  private final AuthenticationManager manager;
  private final TokenProvider provider;

  @Override
  public String authenticate(LoginUserDto dto) {
    Authentication authentication = manager.authenticate(
        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    return provider.generateToken(userDetails.getUsername(), userDetails.getPassword());
  }
}
