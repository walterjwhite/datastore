package com.walterjwhite.datastore.api.model.entity;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

// @PersistenceAware
@Embeddable
@MappedSuperclass
public abstract class AbstractUUIDEntity extends AbstractEntity {
  public AbstractUUIDEntity() {
    super();
    id = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE) + 1;
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
    return id;
  }
}
