package com.walterjwhite.datastore.api.event.post;

import com.walterjwhite.datastore.api.event.AbstractJPAEvent;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public class PostPersistEvent extends AbstractJPAEvent {
  public PostPersistEvent(AbstractEntity entity) {
    super(entity);
  }
}
