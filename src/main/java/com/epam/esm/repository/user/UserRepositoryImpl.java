package com.epam.esm.repository.user;

import com.epam.esm.entity.User;
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
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> query = builder.createQuery(User.class);
    Root<User> rootEntry = query.from(User.class);
    CriteriaQuery<User> all = query.select(rootEntry);
    TypedQuery<User> allQuery = entityManager.createQuery(all);
    allQuery.setFirstResult((page - 1) * pageSize);
    allQuery.setMaxResults(pageSize);
    return allQuery.getResultList();
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
}
