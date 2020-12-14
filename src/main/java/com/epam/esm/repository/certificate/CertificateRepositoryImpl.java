package com.epam.esm.repository.certificate;

import com.epam.esm.entity.GiftCertificate;
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
        entityManager.remove(certificate);
        entityManager.getTransaction().commit();
        return Optional.of(certificate);
    }

    @Override
    public Optional<GiftCertificate> update(GiftCertificate giftCertificate) {
        entityManager.detach(giftCertificate);
        entityManager.getTransaction().begin();
        entityManager.merge(giftCertificate);
        entityManager.getTransaction().commit();
        return Optional.of(giftCertificate);
    }

    @Override
    public List<GiftCertificate> findAllBySpecification(List<CriteriaSpecification<GiftCertificate>> specifications, int page, int pageSize) {
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
        GiftCertificate certificate = entityManager.find(GiftCertificate.class, id);
        entityManager.detach(certificate);
        return Optional.of(certificate);
    }
}

