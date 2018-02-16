package com.walterjwhite.datastore.api.model.entity;

import javax.persistence.*;

/** Entity, or in the case of a CSV, the filename */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class EntityType extends AbstractNamedEntity {
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false)
  protected EntityContainerType entityContainerType;

  public EntityType() {
    super();
  }

  public EntityType(String name, EntityContainerType entityContainerType) {
    super(name);
    this.entityContainerType = entityContainerType;
  }

  public EntityContainerType getEntityContainerType() {
    return entityContainerType;
  }

  public void setEntityContainerType(EntityContainerType entityContainerType) {
    this.entityContainerType = entityContainerType;
  }
}
