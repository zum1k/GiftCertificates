package com.epam.esm.repository.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.CriteriaSpecification;
import com.epam.esm.repository.specification.TagsByCertificateIdCriteriaSpecifications;
import com.epam.esm.repository.specification.TagsByNameCriteriaSpecifications;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TagRepositoryImplTest {
  private final TagRepository tagRepository;

  @Test
  @Transactional
  void addTag_ShouldReturn_True_Test() {
    String expectedName = "tag88";
    Tag tag = new Tag();
    tag.setName(expectedName);

    String actualTagName = tagRepository.add(tag).get().getName();
    assertEquals(expectedName, actualTagName);
  }

  @Test
  @Transactional
  void removeTag_ShouldReturn_True_Test() {
    long expectedId = 9;
    long actualResult = tagRepository.remove(expectedId).get().getTagId();
    ;
    Assertions.assertEquals(expectedId, actualResult);
  }

  @Test
  @Transactional
  void findByName_ShouldReturn_True_Test() {
    String expectedTagName = "test22";
    CriteriaSpecification<Tag> specification =
        new TagsByNameCriteriaSpecifications(expectedTagName);
    String actualTagName = tagRepository.findTagByName(specification).get().getName();
    Assertions.assertEquals(expectedTagName, actualTagName);
  }

  @Test
  @Transactional
  void findById_ShouldReturn_Five_Test() {
    long expectedTagId = 5;
    long actualTagId = tagRepository.findById(expectedTagId).getTagId();
    Assertions.assertEquals(expectedTagId, actualTagId);
  }

  @Test
  @Transactional
  void findAll_ShouldReturn_MoreThanZero_True_Test() {
    int actualSize = tagRepository.findAll().size();
    Assertions.assertTrue(actualSize > 0);
  }

  @Test
  @Transactional
  void findTagsByCertificateId_ShouldReturn_MoreThanZero_True_Test() {
    long certificateId = 6;
    CriteriaSpecification<Tag> specification =
        new TagsByCertificateIdCriteriaSpecifications(certificateId);
    int actualSizeOfTags = tagRepository.findTagsByCertificateId(specification).size();
    Assertions.assertTrue(actualSizeOfTags > 0);
  }
}
