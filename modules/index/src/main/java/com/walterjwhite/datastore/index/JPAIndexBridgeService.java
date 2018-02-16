package com.walterjwhite.datastore.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.criteria.Repository;
import com.walterjwhite.index.api.service.IndexBridgeService;

/** @deprecated This class should live in the index project as a JPA module */
public class JPAIndexBridgeService
    implements IndexBridgeService<AbstractEntity, Class<? extends AbstractEntity>, String> {
  protected final Repository repository;

  public JPAIndexBridgeService(Repository repository) {
    super();
    this.repository = repository;
  }

  @Override
  public AbstractEntity get(Class<? extends AbstractEntity> entityType, String id)
      throws Exception {
    return (repository.get(
        /*(Class<? extends AbstractEntity>) Class.forName(entityType)*/ entityType, id));
  }
}
