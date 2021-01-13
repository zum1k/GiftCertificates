package com.epam.esm.repository.order;

import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.exception.EntityNotFoundException;
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderRepositoryImpl implements OrderRepository {
  private static final String ENTITY_NAME = "Order";
  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<Order> add(Order order) {
    entityManager.persist(order);
    entityManager.flush();
    return Optional.of(order);
  }

  @Override
  public Optional<Order> remove(long id) {
    Order order = entityManager.find(Order.class, id);
    if (order != null) {
      entityManager.remove(order);
      return Optional.of(order);
    }
    throw new EntityNotFoundException(ENTITY_NAME, id);
  }

  @Override
  public Optional<Order> findOrder(long id) {
    Order order = entityManager.find(Order.class, id);
    if (order != null) {
      entityManager.detach(order);
      return Optional.of(order);
    }
    throw new EntityNotFoundException(ENTITY_NAME, id);
  }

  @Override
  public List<Order> findAll(int page, int pageSize) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Order> query = builder.createQuery(Order.class);
    Root<Order> rootEntry = query.from(Order.class);
    CriteriaQuery<Order> all = query.select(rootEntry);
    TypedQuery<Order> allQuery = entityManager.createQuery(all);
    allQuery.setFirstResult((page - 1) * pageSize);
    allQuery.setMaxResults(pageSize);
    return allQuery.getResultList();
  }

  @Override
  public List<Order> findAllBySpecification(CriteriaSpecification<Order> specification, int page, int pageSize) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
    Root<Order> tagRoot = criteriaQuery.from(Order.class);
    criteriaQuery.where(specification.toPredicate(tagRoot, builder));
    TypedQuery<Order> query = entityManager.createQuery(criteriaQuery);
    query.setFirstResult((page - 1) * pageSize);
    query.setMaxResults(pageSize);
    return query.getResultList();
  }

  @Override
  public long count() {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> query = builder.createQuery(Long.class);
    Root<Order> rootEntry = query.from(Order.class);
    query.select(builder.count(rootEntry));
    TypedQuery<Long> allQuery = entityManager.createQuery(query);
    return allQuery.getSingleResult();
  }

  @Override
  public long count(CriteriaSpecification<Order> specification) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

    Root<Order> root = criteriaQuery.from(Order.class);
    criteriaQuery.select(criteriaBuilder.count(root));

    Predicate predicate = specification.toPredicate(root, criteriaBuilder);
    criteriaQuery.where(predicate);
    TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);

    return typedQuery.getSingleResult();
  }

  @Override
  public long count(List<CriteriaSpecification<Order>> specifications) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

    Root<Order> root = criteriaQuery.from(Order.class);
    criteriaQuery.select(criteriaBuilder.count(root));

    List<Predicate> predicates = new ArrayList<>();
    specifications.stream()
        .map(o -> o.toPredicate(root, criteriaBuilder))
        .forEach(predicates::add);

    criteriaQuery.where(predicates.toArray(new Predicate[0]));
    TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
    return typedQuery.getSingleResult();
  }
}
