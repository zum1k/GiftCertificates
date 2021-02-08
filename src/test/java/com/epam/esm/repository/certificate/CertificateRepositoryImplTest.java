package com.epam.esm.repository.certificate;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.CriteriaSpecification;
import com.epam.esm.repository.specification.CertificatesByPartNameCriteriaSpecification;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CertificateRepositoryImplTest {
//  private final CertificateRepository repository;
//
//  @Test
//  @Transactional
//  void findAll_ShouldReturn_Three_True_Test() {
//    int page = 1;
//    int pageSize = 5;
//    int expectedRows = 5;
//
//    int actualRows = repository.findAll(page, pageSize).size();
//    assertEquals(expectedRows, actualRows);
//  }
//
//  @Test
//  @Transactional
//  void findAllCertificates_ShouldReturnFive_True_Test() {
//    String name = "name";
//    CriteriaSpecification<GiftCertificate> specification =
//        new CertificatesByPartNameCriteriaSpecification(name);
//    List<CriteriaSpecification<GiftCertificate>> list = new ArrayList<>();
//    list.add(specification);
//    int page = 1;
//    int pageSize = 5;
//    int expectedRows = 5;
//
//    int actualRows = repository.findAllBySpecification(list, page, pageSize).size();
//    assertEquals(expectedRows, actualRows);
//  }
//
//  @Test
//  @Transactional
//  void removeCertificate_ShouldReturnFive_True_Test() {
//    long expected = 6;
//    long actualRemovedCertificateId = repository.remove(expected).get().getGiftId();
//    assertEquals(expected, actualRemovedCertificateId);
//  }
//
//  @Test
//  @Transactional
//  void updateCertificate_ShouldReturnTrue_Test() {
//    GiftCertificate expectedCertificate = new GiftCertificate();
//    expectedCertificate.setGiftId(6);
//    expectedCertificate.setName("name1updated");
//    expectedCertificate.setDescription("Description 11");
//    expectedCertificate.setPrice(new BigDecimal("100.10"));
//
//    GiftCertificate actualCertificate = repository.update(expectedCertificate).get();
//    assertEquals(expectedCertificate, actualCertificate);
//  }
//
//  @Test
//  @Transactional
//  void findCertificateById_ShouldReturn_Two_True_Test() {
//    String expectedName = "name14";
//    long expectedCertificateId = 15;
//    String actualCertificateName = repository.findById(expectedCertificateId).get().getName();
//    assertEquals(expectedName, actualCertificateName);
//  }
//
//  @Test
//  @Transactional
//  void count_ShouldReturn_TenThousand_True(){
//    long expectedRows = 10005;
//    long actualRows = repository.count();
//    assertEquals(expectedRows, actualRows);
//
//
//  }
//  @Test
//  @Transactional
//  void countBySpecifications_ShouldReturn_Ten(){
//    long expectedRows = 4;
//    String partName = "t";
//    CriteriaSpecification<GiftCertificate> specification = new CertificatesByPartNameCriteriaSpecification(partName);
//    List<CriteriaSpecification<GiftCertificate>> specifications = Collections.singletonList(specification);
//    long actualRows = repository.count(specifications);
//    assertEquals(expectedRows, actualRows);
//
//
//  }
}
