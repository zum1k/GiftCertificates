package com.epam.esm.repository.tag;

import com.epam.esm.configuration.DBTestConfig;
import com.epam.esm.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DBTestConfig.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TagRepositoryImplTest {
    private final TagRepository tagRepository;

    @Test
    void addTag_ShouldReturn_True_Test() {
    }

    @Test
    void removeTag_ShouldReturn_True_Test() {
    }

    @Test
    void findByName() {
    }

   @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findTagsByCertificateId() {
    }
}