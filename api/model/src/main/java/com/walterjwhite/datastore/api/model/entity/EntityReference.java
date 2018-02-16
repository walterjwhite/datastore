package com.walterjwhite.datastore.api.model.entity;

import java.util.Objects;
import javax.persistence.*;

@Entity
public class EntityReference extends AbstractEntity {

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  protected EntityType entityType;

  @Column(nullable = false, updatable = false)
  protected String entityId;

  public EntityReference() {
    super();
  }

  public EntityReference(EntityType entityType, String entityId) {
    super();

    this.entityType = entityType;
    this.entityId = entityId;
  }

  public EntityType getEntityType() {
    return entityType;
  }

  public void setEntityType(EntityType entityType) {
    this.entityType = entityType;
  }

  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EntityReference that = (EntityReference) o;
    return Objects.equals(entityType, that.entityType) && Objects.equals(entityId, that.entityId);
  }

  @Override
  public int hashCode() {

    return Objects.hash(entityType, entityId);
  }
}
