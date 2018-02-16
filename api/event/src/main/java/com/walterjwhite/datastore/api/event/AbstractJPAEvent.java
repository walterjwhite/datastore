package com.walterjwhite.datastore.api.event;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public abstract class AbstractJPAEvent {
  protected final AbstractEntity entity;

  protected AbstractJPAEvent(AbstractEntity entity) {
    super();
    this.entity = entity;
  }

  public AbstractEntity getEntity() {
    return entity;
  }
}
