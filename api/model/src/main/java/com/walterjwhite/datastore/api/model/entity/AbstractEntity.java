package com.walterjwhite.datastore.api.model.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/** TODO: provide a base class where an ID is a UUID where the first few bytes are from the node. */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

  @Id protected String id;

  @Version
  @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
  protected Long version = 0L;

  public String getId() {
    return id;
  }

  /** Called at the time of persistence */
  public void setId(String id) {
    this.id = id;
  }

  public Long getVersion() {
    return (version);
  }

  public void setVersion(final Long version) {
    this.version = version;
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

  @PrePersist
  public void onPrePersist() {
    if (id == null) id = onDoPrePersist();
  }

  protected String onDoPrePersist() {
    return Integer.toString(hashCode());
  }
}
