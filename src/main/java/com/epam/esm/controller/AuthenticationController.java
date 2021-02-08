package com.epam.esm.controller;

import com.epam.esm.entity.dto.LoginUserDto;
import com.epam.esm.entity.dto.UserDto;
import com.epam.esm.security.AuthenticateService;
import com.epam.esm.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@Validated
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {
  private final UserService userService;
  private final AuthenticateService authenticateService;

  @RequestMapping(value = "/signUp",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("permitAll()")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserDto> signUp(@Valid @RequestBody UserDto dto) {
    log.info("register user {}", dto.getEmail());
    UserDto userDto = userService.addUser(dto);
    long dtoId = userDto.getUserId();
    URI resourceUri =
        ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + dtoId).build().toUri();
    return ResponseEntity.created(resourceUri).build();
  }

  @RequestMapping(value = "/signIn",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("permitAll()")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> login(@Valid @RequestBody LoginUserDto dto) {
    log.info("generate token for {}", dto.getEmail());
    String token = authenticateService.authenticate(dto);
    return ResponseEntity.ok().body(token);
  }
}
