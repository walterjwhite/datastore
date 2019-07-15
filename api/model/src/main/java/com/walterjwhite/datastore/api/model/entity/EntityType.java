package com.walterjwhite.datastore.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Entity, or in the case of a CSV, the filename */
@Deprecated
@Getter
@Setter
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class EntityType extends AbstractNamedEntity {
  @Enumerated(EnumType.STRING)
  @Column
  protected EntityContainerType entityContainerType;

  public EntityType(String name, EntityContainerType entityContainerType) {
    super(name);
    this.entityContainerType = entityContainerType;
  }
}
