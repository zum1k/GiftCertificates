package com.epam.esm.service.user;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.entity.dto.UserDto;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.NativeSpecification;
import com.epam.esm.repository.specification.MostWidelyUsedTagByUserOrders;
import com.epam.esm.repository.tag.TagRepository;
import com.epam.esm.repository.user.UserRepository;
import com.epam.esm.service.mapper.tag.TagMapper;
import com.epam.esm.service.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {
  private static final String ENTITY_NAME = "USER";

  private final UserRepository repository;
  private final UserMapper userMapper;
  private final TagRepository tagRepository;
  private final TagMapper tagMapper;

  @Override
  public UserDto findUser(long id) {
    log.info("find user by id {}", id);
    Optional<User> userOptional = repository.find(id);
    return userMapper.toDto(
        userOptional.orElseThrow(() -> new EntityNotFoundException(ENTITY_NAME, id)));
  }

  @Override
  public List<UserDto> findAll(RequestParametersDto dto) {
    log.info("find users");
    List<User> users = repository.findAll(dto.getPage(), dto.getPageLimit());
    return userMapper.toDtoList(users);
  }

  @Override
  @Transactional
  public TagDto findWidelyUsedTagByAllOrdersCost(long userId) {
    log.info("find popular tag by user id {}", userId);
    NativeSpecification<Tag> specification = new MostWidelyUsedTagByUserOrders();
    return tagMapper.toDto(tagRepository.findAll(specification).get(0));
  }

  @Override
  public long count(RequestParametersDto dto) {
    log.info("count user pages");
    int pageSize = dto.getPageLimit();
    long elementsAmount = tagRepository.count();
    return elementsAmount % pageSize == 0
        ? elementsAmount / pageSize
        : elementsAmount / pageSize + 1;
  }
}
