package com.walterjwhite.datastore.api.event.pre;

import com.walterjwhite.datastore.api.event.AbstractJPAEvent;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public class PreRemoveEvent extends AbstractJPAEvent {
  public PreRemoveEvent(AbstractEntity entity) {
    super(entity);
  }
}
