package com.epam.esm.repository.user;

import com.epam.esm.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserRepositoryImplTest {
//  private final UserRepository repository;
//
//  @Test
//  @Transactional
//  void find_ShouldReturn_User_True() {
//    long expectedId = 4;
//    Optional<User> optionalUser = repository.find(expectedId);
//    long actualId = optionalUser.get().getUserId();
//    assertEquals(expectedId, actualId);
//  }
//
//  @Test
//  @Transactional
//  void find_ShouldReturn_Empty_Test() {
//    long expectedId = 300000000;
//    Optional<User> optionalUser = repository.find(expectedId);
//    assertEquals(Optional.empty(), optionalUser);
//  }
//
//  @Test
//  @Transactional
//  void findAll_ShouldReturn_Five_True() {
//    int expectedSize = 10;
//    int page = 1;
//    int pageSize = 10;
//    int actualSize = repository.findAll(page, pageSize).size();
//    assertEquals(expectedSize, actualSize);
//  }
//
//  @Test
//  @Transactional
//  void count_ShouldReturn_AllUsers() {
//    long expectedUsers = 1001;
//    long actualRows = repository.count();
//    assertEquals(expectedUsers, actualRows);
//  }
}