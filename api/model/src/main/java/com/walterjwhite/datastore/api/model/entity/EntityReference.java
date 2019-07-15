package com.walterjwhite.datastore.api.model.entity;

import com.walterjwhite.datastore.api.annotation.PrimaryKey;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Deprecated
@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class EntityReference extends AbstractEntity {
  @PrimaryKey
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false)
  protected EntityType entityType;

  @PrimaryKey @Column protected Integer entityId;
}
