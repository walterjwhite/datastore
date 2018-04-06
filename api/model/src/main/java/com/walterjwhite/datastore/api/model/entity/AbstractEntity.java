package com.walterjwhite.datastore.api.model.entity;

import java.io.Serializable;
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

  public abstract boolean equals(Object o);

  public abstract int hashCode();

  @PrePersist
  public void onPrePersist() {
    if (id == null) id = onDoPrePersist();
  }

  /**
   * This serves to log the hashCode as well as allow the id generation strategy to be overridden.
   *
   * @return the id for this entity, the hashCode by default, could be UUID.
   */
  protected String onDoPrePersist() {
    return Integer.toString(hashCode());
  }
}
