package com.walterjwhite.datastore.api.event.post;

import com.walterjwhite.datastore.api.event.AbstractJPAEvent;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public class PostRemoveEvent extends AbstractJPAEvent {
  public PostRemoveEvent(AbstractEntity entity) {
    super(entity);
  }
}
