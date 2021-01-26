package com.epam.esm.repository.user;

import com.epam.esm.entity.User;
import com.epam.esm.repository.CriteriaSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepositoryImpl implements UserRepository {
  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<User> find(long id) {
    return Optional.ofNullable(entityManager.find(User.class, id));
  }

  @Override
  public List<User> findAll(int page, int pageSize) {
    TypedQuery<User> allQuery = typedQuery();
    allQuery.setFirstResult((page - 1) * pageSize);
    allQuery.setMaxResults(pageSize);
    return allQuery.getResultList();
  }

  @Override
  public Optional<User> findByEmail(CriteriaSpecification<User> specification) {
    TypedQuery<User> query = entityManager.createQuery(mapQuery(specification));
    return query.getResultStream().findFirst();
  }

  @Override
  public long count() {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> query = builder.createQuery(Long.class);
    Root<User> rootEntry = query.from(User.class);
    query.select(builder.count(rootEntry));
    TypedQuery<Long> allQuery = entityManager.createQuery(query);
    return allQuery.getSingleResult();
  }

  private TypedQuery<User> typedQuery() {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> query = builder.createQuery(User.class);
    Root<User> rootEntry = query.from(User.class);
    CriteriaQuery<User> all = query.select(rootEntry);
    return entityManager.createQuery(all);
  }

  private CriteriaQuery<User> mapQuery(CriteriaSpecification<User> specification) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
    Root<User> tagRoot = criteriaQuery.from(User.class);
    return criteriaQuery.where(specification.toPredicate(tagRoot, builder));
  }
}
