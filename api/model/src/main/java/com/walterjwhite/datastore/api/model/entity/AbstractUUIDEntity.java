package com.walterjwhite.datastore.api.model.entity;

import java.util.UUID;
import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractUUIDEntity extends AbstractEntity {

  @Override
  protected String onDoPrePersist() {
    return UUID.randomUUID().toString();
  }
}
