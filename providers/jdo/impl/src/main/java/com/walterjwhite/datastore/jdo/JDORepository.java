package com.walterjwhite.datastore.jdo;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import com.walterjwhite.datastore.api.repository.Repository;
import java.util.List;
import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.transaction.Transaction;
import lombok.Getter;

@Getter
public class JDORepository implements Repository {
  protected final PersistenceManager persistenceManager;
  // protected final XAResource xaResource;

  @Inject
  public JDORepository(PersistenceManager persistenceManager) {
    this.persistenceManager = persistenceManager;
  }

  @Override
  public <EntityType extends AbstractEntity> EntityType create(EntityType entity) {
    return persistenceManager.makePersistent(entity);
  }

  @Override
  public <EntityType extends AbstractEntity> EntityType update(EntityType entity) {
    return persistenceManager.makePersistent(entity);
  }

  @Override
  public void refresh(AbstractEntity entity) {
    persistenceManager.refresh(entity);
  }

  @Override
  public void delete(AbstractEntity entity) {
    persistenceManager.deletePersistent(entity);
  }

  @Override
  public Transaction getTransaction() {
    // return persistenceManager.currentTransaction();
    return null;
  }

  @Override
  public <EntityType extends AbstractEntity> EntityType findById(
      Class<EntityType> entityTypeClass, Integer id) {
    return persistenceManager.getObjectById(entityTypeClass, id);
  }

  @Override
  public <EntityType extends AbstractEntity> List<EntityType> findAll(
      Class<EntityType> entityTypeClass) {
    // return persistenceManager.;
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <EntityType extends AbstractEntity, ResultType> ResultType query(
      QueryConfiguration<EntityType, ResultType> query) {
    return null;
  }
}
