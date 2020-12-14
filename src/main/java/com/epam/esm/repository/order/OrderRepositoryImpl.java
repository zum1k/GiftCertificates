package com.epam.esm.repository.order;

import com.epam.esm.entity.Order;
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
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<Order> add(Order order) {
        entityManager.persist(order);
        entityManager.flush();
        return Optional.of(order);
    }

    @Override
    public Optional<Order> remove(long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        entityManager.remove(order);
        entityManager.getTransaction().commit();
        return Optional.of(order);
    }

    @Override
    public Optional<Order> findOrder(long id) {
        Order order = entityManager.find(Order.class, id);
        entityManager.detach(order);
        return Optional.of(order);
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
}
