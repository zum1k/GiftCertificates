package com.epam.esm.repository.specification;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.CriteriaSpecification;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class PopularTagUsedByUserAllOrders implements CriteriaSpecification<Tag> {
  private long userId;

  @Override
  public Predicate toPredicate(Root<Tag> root, CriteriaBuilder criteriaBuilder) {
    Join<Tag, GiftCertificate> join = root.join("gifts");
    return null;
  }
}
//  private static final String QUERY = "SELECT Tag.id, Tag.name\n" +
//          "FROM \"Tag\" Tag\n" +
//          "       JOIN \"GiftCertificateTag\" GCT ON tag.id = GCT.tag_id\n" +
//          "       JOIN \"GiftCertificate\" GC on GCT.gift_certificate_id = GC.id\n" +
//          "       JOIN \"Order\" O on GC.id = O.gift_certificate_id\n" +
//          "       JOIN (SELECT user_id\n" +
//          "             from \"Order\"\n" +
//          "             group by user_id\n" +
//          "             ORDER BY SUM(price) DESC\n" +
//          "             LIMIT 1) as userId on O.user_id = userId.user_id\n" +
//          "GROUP BY Tag.id\n" +
//          "ORDER BY count(Tag.id) DESC\n" +
//          "LIMIT 1;";

