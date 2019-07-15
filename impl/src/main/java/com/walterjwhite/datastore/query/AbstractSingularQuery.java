package com.walterjwhite.datastore.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public abstract class AbstractSingularQuery<EntityType extends AbstractEntity>
    extends AbstractQuery<EntityType, EntityType> {
  public AbstractSingularQuery(Class<EntityType> entityTypeClass, boolean construct) {
    super(0, 1, entityTypeClass, entityTypeClass, construct);
  }
}
