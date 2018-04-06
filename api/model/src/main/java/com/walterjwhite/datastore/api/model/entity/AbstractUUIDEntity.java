package com.walterjwhite.datastore.api.model.entity;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractUUIDEntity extends AbstractEntity {

  @Override
  protected String onDoPrePersist() {
    return UUID.randomUUID().toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractEntity that = (AbstractEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
