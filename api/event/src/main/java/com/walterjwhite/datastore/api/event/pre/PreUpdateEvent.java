package com.walterjwhite.datastore.api.event.pre;

import com.walterjwhite.datastore.api.event.AbstractJPAEvent;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public class PreUpdateEvent extends AbstractJPAEvent {
  public PreUpdateEvent(AbstractEntity entity) {
    super(entity);
  }
}
