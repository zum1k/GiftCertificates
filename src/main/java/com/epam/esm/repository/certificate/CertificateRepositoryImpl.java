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
    public List<GiftCertificate> findAllBySpecification(CriteriaSpecification<GiftCertificate> specification) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> criteriaQuery = builder.createQuery(GiftCertificate.class);

        Root<GiftCertificate> tagRoot = criteriaQuery.from(GiftCertificate.class);
        Predicate predicate = specification.toPredicate(tagRoot, builder);
        criteriaQuery.where(predicate);

        TypedQuery<GiftCertificate> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<GiftCertificate> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> query = builder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> rootEntry = query.from(GiftCertificate.class);
        CriteriaQuery<GiftCertificate> all = query.select(rootEntry);
        TypedQuery<GiftCertificate> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        GiftCertificate certificate = entityManager.find(GiftCertificate.class, id);
        entityManager.detach(certificate);
        return Optional.of(certificate);
    }
}

