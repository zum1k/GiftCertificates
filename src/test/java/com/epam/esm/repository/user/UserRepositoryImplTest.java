package com.epam.esm.repository.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserRepositoryImplTest {
    private final UserRepository repository;

    @Test
    @Transactional
    void find_ShouldReturn_User_True() {
        long expectedId = 4;
        long actualId = repository.find(expectedId).get().getUserId();
        assertEquals(expectedId, actualId);
    }

    @Test
    @Transactional
    void findAll_ShouldReturn_Five_True() {
        int expectedSize = 10;
        int page = 1;
        int pageSize = 10;
        int actualSize = repository.findAll(page, pageSize).size();
        assertEquals(expectedSize, actualSize);
    }
}