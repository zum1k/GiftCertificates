package com.epam.esm.repository.specification;

import com.epam.esm.entity.User;
import com.epam.esm.repository.CriteriaSpecification;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class UsersByEmailCriteriaSpecification implements CriteriaSpecification<User> {
  private final String email;

  @Override
  public Predicate toPredicate(Root<User> root, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.equal(root.get("email"), email);
  }
}
