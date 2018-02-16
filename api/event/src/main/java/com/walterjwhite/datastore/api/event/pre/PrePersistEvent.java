package com.walterjwhite.datastore.api.event.pre;

import com.walterjwhite.datastore.api.event.AbstractJPAEvent;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public class PrePersistEvent extends AbstractJPAEvent {
  public PrePersistEvent(AbstractEntity entity) {
    super(entity);
  }
}
