package com.epam.esm.repository.user;

import com.epam.esm.entity.User;
import com.epam.esm.repository.CriteriaSpecification;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
  Optional<User> find(long id);

  List<User> findAll(int page, int pageSize);

  long count();

  long count(CriteriaSpecification<User> specification);

  long count(List<CriteriaSpecification<User>> specifications);
}
