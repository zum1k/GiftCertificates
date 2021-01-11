package com.epam.esm.repository.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.CriteriaSpecification;
import com.epam.esm.repository.NativeSpecification;
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
public class TagRepositoryImpl implements TagRepository {
    private final static String ENTITY_NAME = "Tag";
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
        if(tag!=null) {
            entityManager.remove(tag);
            return Optional.of(tag);
        }
        throw new EntityNotFoundException(ENTITY_NAME,id);
    }

    @Override
    public Optional<Tag> findTagByName(CriteriaSpecification<Tag> specification) {
        TypedQuery<Tag> query = entityManager.createQuery(mapQuery(specification));
        return query.getResultStream().findFirst();
    }

    @Override
    public Tag findById(long id) {
        Tag tag = entityManager.find(Tag.class, id);
        if (tag != null) {
          //  entityManager.detach(tag);
            return tag;
        }
        throw new EntityNotFoundException(ENTITY_NAME, id);
    }

    @Override
    public List<Tag> findAll(int page, int pageSize) {
        TypedQuery<Tag> allQuery = typedQuery();
        allQuery.setFirstResult((page - 1) * pageSize);
        allQuery.setMaxResults(pageSize);
        return allQuery.getResultList();
    }

    @Override
    public List<Tag> findTagsByCertificateId(CriteriaSpecification<Tag> specification) {
        TypedQuery<Tag> query = entityManager.createQuery(mapQuery(specification));
        return query.getResultList();
    }

    @Override
    public List<Tag> findAll() {
        TypedQuery<Tag> allQuery = typedQuery();
        return allQuery.getResultList();
    }

    @Override
    public List findAll(NativeSpecification<Tag> specification) {
        String nativeQuery = specification.getNativeQuery();
        return entityManager.createNativeQuery(nativeQuery,Tag.class).getResultList();
    }

    private TypedQuery<Tag> typedQuery() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> rootEntry = query.from(Tag.class);
        CriteriaQuery<Tag> all = query.select(rootEntry);
        return  entityManager.createQuery(all);
    }

    private CriteriaQuery<Tag> mapQuery(CriteriaSpecification<Tag> specification) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = builder.createQuery(Tag.class);
        Root<Tag> tagRoot = criteriaQuery.from(Tag.class);
        return criteriaQuery.where(specification.toPredicate(tagRoot, builder));
    }
}


