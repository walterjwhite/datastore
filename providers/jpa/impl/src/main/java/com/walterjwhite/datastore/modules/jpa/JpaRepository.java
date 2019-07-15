package com.walterjwhite.datastore.modules.jpa;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.QueryBuilder;
import com.walterjwhite.datastore.api.repository.QueryBuilderResolver;
import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.datastore.query.FindAllQuery;
import com.walterjwhite.datastore.util.EntityInstanceUtil;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transaction;
import lombok.Getter;

/**
 * In order to easily and "conveniently" support multiple persistence units, this class is abstract
 * The "correct" EntityManager will be injected by extending this class and annotating the requested
 * entity manager
 */
@Getter
public class JpaRepository implements Repository {
  // TODO: if we need to bind a secondary EM, how to do that in a reusable manner?
  protected final EntityManager entityManager;
  protected final QueryBuilderResolver queryBuilderResolver;

  @Inject
  public JpaRepository(EntityManager entityManager, QueryBuilderResolver queryBuilderResolver) {
    super();
    this.entityManager = entityManager;
    this.queryBuilderResolver = queryBuilderResolver;
  }

  public <EntityType extends AbstractEntity> EntityType create(EntityType entity) {
    entityManager.persist(entity);
    return (entity);
  }

  public <EntityType extends AbstractEntity> EntityType update(EntityType entity) {
    return (entityManager.merge(entity));
  }

  public void refresh(AbstractEntity entity) {
    entityManager.refresh(entity);
  }

  public void delete(AbstractEntity entity) {
    entityManager.remove(entity);
  }

  public AbstractEntity get(Class<? extends AbstractEntity> entityClass, final String id) {
    return entityManager.find(entityClass, id);
  }

  public Transaction getTransaction() {
    /*return entityManager.getTransaction();*/
    return null;
  }

  public <EntityType extends AbstractEntity> List<EntityType> findAll(
      Class<EntityType> entityTypeClass) {
    return (List<EntityType>) query(new FindAllQuery<>(0, -1, entityTypeClass));
  }

  public <EntityType extends AbstractEntity> EntityType findById(
      final Class<EntityType> entityTypeClass, final Integer id) {
    return entityManager.find(entityTypeClass, id);
  }

  public <EntityType extends AbstractEntity, ResultType extends Object> ResultType query(
      QueryConfiguration<EntityType, ResultType> query) {
    try {
      final QueryBuilder builder = queryBuilderResolver.getBuilder(query);
      return (ResultType) builder.get(entityManager, query);
    } catch (NoResultException e) {
      return handleAutoCreate(query, e);
    }
  }

  protected <EntityType extends AbstractEntity, ResultType extends Object>
      ResultType handleAutoCreate(
          QueryConfiguration<EntityType, ResultType> query, NoResultException e) {
    if (query.supportsAutoCreate()) {
      if (query.isConstruct()) {

        final Object[] createArguments = query.getAutoCreateArguments();
        if (createArguments.length == 1
            && createArguments[0].getClass().equals(query.getResultTypeClass()))
          return (ResultType) doCreate((EntityType) createArguments[0]);

        return (ResultType)
            doCreate(
                EntityInstanceUtil.instantiateNewObject(
                    query.getEntityTypeClass(), createArguments));
      }

      // TODO: simplify the construction, coalesce isConstruct and Create
      //      return (ResultType)
      //          create(query.getPersistenceOptions()[0].getTransientEntity());
    }

    throw (e);
  }

  protected <EntityType extends AbstractEntity> EntityType doCreate(EntityType entity) {
    if (entity == null)
      throw new IllegalArgumentException(
          "Requested auto-create, but arguments are null, unable to instantiate, application is mis-configured");

    // TODO: use annotations here ...
    // EntityTransaction entityTransaction = getEntityTransaction();
    // entityTransaction.begin();

    try {

      entityManager.persist(entity);
      // entityTransaction.commit();

      return entity;
    } catch (Exception e) {
      // entityTransaction.rollback();
      throw e;
    }
  }
}
