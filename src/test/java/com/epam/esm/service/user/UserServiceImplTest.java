package com.epam.esm.service.user;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.entity.dto.UserDto;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.specification.MostWidelyUsedTagByUserOrders;
import com.epam.esm.repository.tag.TagRepository;
import com.epam.esm.repository.user.UserRepository;
import com.epam.esm.service.mapper.tag.TagMapper;
import com.epam.esm.service.mapper.user.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @InjectMocks
  private UserServiceImpl service;
  @Mock
  private UserRepository repository;
  @Mock
  private TagRepository tagRepository;
  @Mock
  private UserMapper userMapper;
  @Mock
  private TagMapper tagMapper;

  @Test
  void findUser_ShouldReturn_User_Test() {
    long userId = 1;
    UserDto userDto = new UserDto();
    userDto.setUserId(userId);
    User user = new User();
    user.setUserId(userId);
    Mockito.when(repository.find(userId)).thenReturn(Optional.of(user));
    Mockito.when(userMapper.toDto(user)).thenReturn(userDto);

    UserDto actualUser = service.findUser(userId);
    Assertions.assertEquals(userDto, actualUser);
  }

  @Test
  void findUser_ShouldReturn_Exception_Test() {
    long expectedId = 1;
    Mockito.when(repository.find(expectedId)).thenReturn(Optional.empty());
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> {
          service.findUser(expectedId);
        });
    Mockito.verify(repository).find(Mockito.eq(expectedId));
  }

  @Test
  void findAll_ShouldReturn_CorrectValue_Test() {
    String password = "password";
    String email = "email";
    String firstName = "firstName";
    String lastName = "lastName";
    int page = 1;
    int pageSize = 5;
    RequestParametersDto parametersDto = new RequestParametersDto();
    parametersDto.setPage(page);
    parametersDto.setPageLimit(pageSize);

    List<UserDto> userDtos = new ArrayList<>();
    List<User> users = new ArrayList<>();
    for (int i = 1; i < 6; i++) {
      User user = new User();
      user.setUserId(i);
      user.setEmail(email + i);
      user.setFirstName(firstName + i);
      user.setLastName(lastName);
      user.setPassword(password + i);
      users.add(user);

      UserDto userDto = new UserDto();
      userDto.setUserId(i);
      userDto.setEmail(email + i);
      userDto.setFirstName(firstName + i);
      userDto.setLastName(lastName);
      userDto.setPassword(password + i);
      userDtos.add(userDto);
    }
    Mockito.when(repository.findAll(page, pageSize)).thenReturn(users);
    Mockito.when(userMapper.toDtoList(users)).thenReturn(userDtos);

    List<UserDto> actualUserDtos = service.findAll(parametersDto);
    Assertions.assertEquals(userDtos, actualUserDtos);
  }

  @Test
  void count_ShouldReturn_CorrectValue_Test() {
    long expectedAmount = 1000;
    long expectedCount = 10;
    RequestParametersDto dto = new RequestParametersDto();
    dto.setPageLimit(100);
    Mockito.when(repository.count()).thenReturn(expectedAmount);
    long actualCount = service.count(dto);
    Assertions.assertEquals(expectedCount, actualCount);

  }

  @Test
  void findWidelyUsedTagByAllOrdersCost_ShouldReturn_Tag_Test() {
    long expectedTagId = 475;
    long userId = 1;
    TagDto tagDto = new TagDto();
    tagDto.setId((int) expectedTagId);
    List<Tag> tags = Collections.singletonList(new Tag());
    Mockito.when(tagRepository.findAll(Mockito.any(MostWidelyUsedTagByUserOrders.class))).thenReturn(tags);
    Mockito.when(tagMapper.toDto(Mockito.any(Tag.class))).thenReturn(tagDto);
    long actualTagId = service.findWidelyUsedTagByAllOrdersCost(userId).getId();
    assertEquals(expectedTagId, actualTagId);
  }
}
