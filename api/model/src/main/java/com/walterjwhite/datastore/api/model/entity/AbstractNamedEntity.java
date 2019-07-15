package com.walterjwhite.datastore.api.model.entity;

import com.walterjwhite.datastore.api.annotation.Unique;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import lombok.*;

/** Base class to be used for named entities. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
// @PersistenceAware
@Embeddable
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractEntity {

  /** Name of the entity, to be short, a quick reference. */
  @Unique
  @Column(unique = true, nullable = false)
  protected String name;

  /** description of the entity, longer, more meaningful. */
  @EqualsAndHashCode.Exclude @Column @Lob protected String description;

  protected AbstractNamedEntity(String name) {
    super();
    this.name = name;
  }
}
