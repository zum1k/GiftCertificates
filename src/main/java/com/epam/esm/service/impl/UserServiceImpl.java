package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.User;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.user.UserRepository;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User findUser(long id) {
        log.info("find user by id {}", id);
        return repository.find(id).orElseThrow(() -> new EntityNotFoundException("not found", id));
    }

    @Override
    public List<User> findAll(RequestParametersDto dto) {
        log.info("find users");
        return repository.findAll(dto.getPage(), dto.getPageLimit());
    }

    @Override
    public Tag findWidelyUsedTagByAllOrdersCost(long userId) {
        log.info("find tag by user id {}", userId);
        return null;
    }
}
