package com.epam.esm.repository.user;

import com.epam.esm.entity.User;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public Optional<User> find(long id) {
        return Optional.empty();
    }
}
