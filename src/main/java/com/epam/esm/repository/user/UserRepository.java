package com.epam.esm.repository.user;

import com.epam.esm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> find(long id);

    List<User> findAll(int page, int pageSize);
}
