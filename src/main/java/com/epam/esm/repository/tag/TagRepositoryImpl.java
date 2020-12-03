package com.epam.esm.repository.tag;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.EntityNotFoundException;
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
public class TagRepositoryImpl implements TagRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<Tag> add(Tag tag) {
        entityManager.persist(tag);
        entityManager.flush();
        return Optional.of(tag);
    }

    @Override
    public Optional<Tag> remove(long id) {
        Tag tag = entityManager.find(Tag.class, id);
        entityManager.remove(tag);
        entityManager.getTransaction().commit();
        return Optional.of(tag);
    }

    @Override
    public Optional<Tag> findByName(String tagName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = builder.createQuery(Tag.class);

        Root<Tag> tagRoot = criteriaQuery.from(Tag.class);
        Predicate tagNamePredicate = builder.equal(tagRoot.get("name"), tagName);
        criteriaQuery.where(tagNamePredicate);

        TypedQuery<Tag> query = entityManager.createQuery(criteriaQuery);
        return Optional.of(query.getSingleResult());
    }

    @Override
    public Tag findById(long id) {
        Tag tag = entityManager.find(Tag.class, id);
        return Optional.ofNullable(tag).orElseThrow(() -> new EntityNotFoundException("not found", id));
    }

    @Override
    public List<Tag> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> rootEntry = query.from(Tag.class);
        CriteriaQuery<Tag> all = query.select(rootEntry);
        TypedQuery<Tag> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<Tag> findTagsByCertificateId(long certificateId) {
        return null;
    }
}


