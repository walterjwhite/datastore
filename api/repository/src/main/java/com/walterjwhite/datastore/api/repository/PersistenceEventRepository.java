package com.walterjwhite.datastore.api.repository;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.util.List;
import javax.transaction.Transaction;

/**
 * Provides an agnostic API (criteria / querydsl / ES / SOLR / etc.) for querying data backed by
 * JPA.
 */
public interface PersistenceEventRepository {
  <EntityType extends AbstractEntity> EntityType persist(EntityType entity);

  void refresh(AbstractEntity entity);

  void delete(AbstractEntity entity);

  // TODO: remove this, should we have access to this?
  // PersistenceManager getPersistenceManager();

  // TODO: wrap this with a JTA transaction
  Transaction getTransaction();

  <EntityType extends AbstractEntity> EntityType findById(
      final Class<EntityType> entityTypeClass, final Integer id);

  <EntityType extends AbstractEntity> List<EntityType> findAll(
      final Class<EntityType> entityTypeClass);

  <EntityType extends AbstractEntity, ResultType extends Object> ResultType query(
      QueryConfiguration<EntityType, ResultType> query);
}
