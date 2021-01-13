package com.epam.esm.repository.certificate;

import com.epam.esm.entity.GiftCertificate;
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
public class CertificateRepositoryImpl implements CertificateRepository {
  private static final String ENTITY_NAME = "Certificate";
  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<GiftCertificate> add(GiftCertificate certificate) {
    entityManager.persist(certificate);
    entityManager.flush();
    return Optional.of(certificate);
  }

  @Override
  public Optional<GiftCertificate> remove(long id) {
    GiftCertificate certificate = entityManager.find(GiftCertificate.class, id);
    if (certificate != null) {
      entityManager.remove(certificate);
      return Optional.of(certificate);
    }
    throw new EntityNotFoundException(ENTITY_NAME, id);
  }

  @Override
  public Optional<GiftCertificate> update(GiftCertificate giftCertificate) {
    entityManager.detach(giftCertificate);
    entityManager.merge(giftCertificate);
    return Optional.of(giftCertificate);
  }

  @Override
  public List<GiftCertificate> findAllBySpecification(
      List<CriteriaSpecification<GiftCertificate>> specifications, int page, int pageSize) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<GiftCertificate> criteriaQuery = builder.createQuery(GiftCertificate.class);
    Root<GiftCertificate> root = criteriaQuery.from(GiftCertificate.class);
    List<Predicate> predicates = new ArrayList<>();
    specifications.forEach(o -> predicates.add(o.toPredicate(root, builder)));
    criteriaQuery.where(predicates.toArray(new Predicate[]{}));
    TypedQuery<GiftCertificate> query = entityManager.createQuery(criteriaQuery);
    query.setFirstResult((page - 1) * pageSize);
    query.setMaxResults(pageSize);
    return query.getResultList();
  }

  @Override
  public List<GiftCertificate> findAll(int page, int pageSize) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<GiftCertificate> query = builder.createQuery(GiftCertificate.class);
    Root<GiftCertificate> rootEntry = query.from(GiftCertificate.class);
    CriteriaQuery<GiftCertificate> all = query.select(rootEntry);
    TypedQuery<GiftCertificate> allQuery = entityManager.createQuery(all);
    allQuery.setFirstResult((page - 1) * pageSize);
    allQuery.setMaxResults(pageSize);
    return allQuery.getResultList();
  }

  @Override
  public Optional<GiftCertificate> findById(long id) {
    return Optional.ofNullable(entityManager.find(GiftCertificate.class, id));
  }

  @Override
  public long count() {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> query = builder.createQuery(Long.class);
    Root<GiftCertificate> rootEntry = query.from(GiftCertificate.class);
    query.select(builder.count(rootEntry));
    TypedQuery<Long> allQuery = entityManager.createQuery(query);
    return allQuery.getSingleResult();
  }

  @Override
  public long count(CriteriaSpecification<GiftCertificate> specification) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

    Root<GiftCertificate> root = criteriaQuery.from(GiftCertificate.class);
    criteriaQuery.select(criteriaBuilder.count(root));

    Predicate predicate = specification.toPredicate(root, criteriaBuilder);
    criteriaQuery.where(predicate);
    TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);

    return typedQuery.getSingleResult();
  }

  @Override
  public long count(List<CriteriaSpecification<GiftCertificate>> specifications) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

    Root<GiftCertificate> root = criteriaQuery.from(GiftCertificate.class);
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
