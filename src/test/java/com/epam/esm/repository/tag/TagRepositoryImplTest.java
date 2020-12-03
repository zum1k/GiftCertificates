package com.epam.esm.repository.tag;

import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//@ContextConfiguration(classes = {DBTestConfig.class})
@ExtendWith(SpringExtension.class)
class TagRepositoryImplTest {
    private final TagRepository tagRepository;

    @Test
    void addTag_ShouldReturn_True_Test() {
        //TODO
        Tag tag = new Tag("tag11");
        int expectedSize = tagRepository.findAll().size() + 1;
        tagRepository.add(tag);
        int actualResult = tagRepository.findAll().size();
        Assertions.assertEquals(expectedSize, actualResult);
    }

    @Test
    void removeTag_ShouldReturn_True_Test() {
        //TODO
        long tagId = 1;
        int expectedId = tagRepository.findAll().size() - 1;
        tagRepository.remove(tagId);
        int actualResult = tagRepository.findAll().size();
        Assertions.assertEquals(expectedId, actualResult);
    }

    @Test
    void findByName_ShouldReturn_True_Test() {
        String expectedTagName = "name2";
        String actualTagName = tagRepository.findByName(expectedTagName).get().getName();
        Assertions.assertEquals(expectedTagName, actualTagName);
    }

    @Test
    void findById_ShouldReturn_Five_Test() {
        long expectedTagId = 5;
        long actualTagId = tagRepository.findById(expectedTagId).get().getTagId();
        Assertions.assertEquals(expectedTagId, actualTagId);
    }

    @Test
    void findAll_ShouldReturn_MoreThanZero_True_Test() {
        int actualSize = tagRepository.findAll().size();
        Assertions.assertTrue(actualSize > 0);
    }

    @Test
    void findTagsByCertificateId_ShouldReturn_MoreThanZero_True_Test() {
        long certificateId = 2;
        int actualSizeOfTags = tagRepository.findTagsByCertificateId(certificateId).size();
        Assertions.assertTrue(actualSizeOfTags > 0);
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @Autowired
    @SuppressWarnings("all")
    public TagRepositoryImplTest(final TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    //</editor-fold>
}
