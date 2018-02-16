package com.walterjwhite.datastore.api.event.post;

import com.walterjwhite.datastore.api.event.AbstractJPAEvent;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public class PostUpdateEvent extends AbstractJPAEvent {
  public PostUpdateEvent(AbstractEntity entity) {
    super(entity);
  }
}
