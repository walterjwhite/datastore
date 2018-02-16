package com.walterjwhite.datastore.criteria;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Repository<EntityType extends AbstractEntity> {
  private static final Logger LOGGER = LoggerFactory.getLogger(Repository.class);
  protected final EntityManager entityManager;
  protected final CriteriaBuilder criteriaBuilder;

  @Inject
  public Repository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super();
    this.entityManager = entityManager;
    this.criteriaBuilder = criteriaBuilder;
  }

  public EntityType persist(EntityType entity) {
    entityManager.persist(entity);
    return (entity);
  }

  public EntityType merge(EntityType entity) {
    return (entityManager.merge(entity));
  }

  public void refresh(EntityType entity) {
    entityManager.refresh(entity);
  }

  public void delete(EntityType entity) {
    entityManager.remove(entity);
  }

  //  @Transactional
  public List<? extends AbstractEntity> findAll(Class<? extends AbstractEntity> entityClass) {
    return entityManager
        .createQuery(getCriteriaQuery(entityClass).getCriteriaQuery())
        .getResultList();
  }

  public List<? extends AbstractEntity> findAll(
      Class<? extends AbstractEntity> entityClass, int offset, int limit) {
    return entityManager
        .createQuery(getCriteriaQuery(entityClass).getCriteriaQuery())
        .setFirstResult(offset)
        .setMaxResults(limit)
        .getResultList();
  }

  public int count(Class<? extends AbstractEntity> entityClass, Predicate... conditions) {
    CriteriaQuery<Long> criteriaQuery =
        criteriaBuilder.createQuery(Long.class).where(criteriaBuilder.and(conditions));
    Root<? extends AbstractEntity> root = criteriaQuery.from(entityClass);
    criteriaQuery.select(criteriaBuilder.count(root));
    return (count(criteriaQuery, root));
  }

  public int count(CriteriaQuery criteriaQuery, Root<? extends AbstractEntity> root) {
    criteriaQuery.select(criteriaBuilder.count(root));
    return (((Long) entityManager.createQuery(criteriaQuery).getSingleResult()).intValue());
  }

  public int count(CriteriaQuery criteriaQuery) {
    return (count(
        criteriaQuery,
        (Root<? extends AbstractEntity>) criteriaQuery.getRoots().iterator().next()));
  }

  public List<EntityType> get(
      Class<? extends AbstractEntity> entityClass,
      final int offset,
      final int recordCount,
      Predicate... conditions) {
    CriteriaQuery<? extends AbstractEntity> criteriaQuery =
        criteriaBuilder.createQuery(entityClass).where(criteriaBuilder.and(conditions));
    criteriaQuery.from(entityClass);

    return ((List<EntityType>)
        entityManager
            .createQuery(criteriaQuery)
            .setFirstResult((int) offset)
            .setMaxResults(recordCount)
            .getResultList());
  }

  // @NOTE: this merely issues n+1 calls to the database to fetch items
  // 1 to do count
  // 1 for each row
  // for stream, we should collect 10 or so at a time ...
  public Iterator<EntityType> iterate(
      Class<? extends AbstractEntity> entityClass, Predicate... conditions) {
    CriteriaQuery<EntityType> criteriaQuery =
        (CriteriaQuery<EntityType>) criteriaBuilder.createQuery(entityClass);
    criteriaQuery.where(conditions);

    final Provider<Repository> repositoryProvider = null;
    return (new ResultsetIterator<>(criteriaQuery, repositoryProvider));
  }

  public EntityType get(
      Class<? extends AbstractEntity> entityClass, final int offset, Predicate... conditions) {
    return (get(entityClass, offset, 1, conditions).get(0));
  }

  public Object get(CriteriaQuery criteriaQuery, final int offset) {
    return (entityManager
        .createQuery(criteriaQuery)
        .setFirstResult(offset)
        .setMaxResults(1)
        .getSingleResult());
  }

  public List get(CriteriaQuery criteriaQuery) {
    return entityManager.createQuery(criteriaQuery).getResultList();
  }

  public AbstractEntity get(Class<? extends AbstractEntity> entityClass, final String id) {
    return entityManager.find(entityClass, id);
  }

  protected CriteriaQueryConfiguration<? extends AbstractEntity> getCriteriaQuery(
      Class<? extends AbstractEntity> entityClass) {

    CriteriaQuery<? extends AbstractEntity> criteriaQuery =
        criteriaBuilder.createQuery(entityClass);
    Root<? extends AbstractEntity> root = criteriaQuery.from(entityClass);

    final CriteriaQueryConfiguration<? extends AbstractEntity> criteriaQueryConfiguration =
        new CriteriaQueryConfiguration(criteriaQuery, root);
    return (criteriaQueryConfiguration);
  }

  public EntityType findRandom(
      Class<? extends AbstractEntity> entityClass, Predicate... conditions) {
    return (get(entityClass, new Random().nextInt(count(entityClass, conditions)), 1, conditions)
        .get(0));
  }

  public EntityManager getEntityManager() {
    return entityManager;
  }

  public EntityTransaction getEntityTransaction() {
    return entityManager.getTransaction();
  }
}
