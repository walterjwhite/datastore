package com.walterjwhite.datastore.api.model.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/** Base class to be used for named entities. */
@MappedSuperclass
// @Embeddable
public abstract class AbstractNamedEntity extends AbstractEntity {

  /** Name of the entity, to be short, a quick reference. */
  @Column(unique = true)
  protected String name;

  /** description of the entity, longer, more meaningful. */
  @Column protected String description;

  protected AbstractNamedEntity() {
    super();
  }

  protected AbstractNamedEntity(String name) {
    super();
    this.name = name;
  }

  protected AbstractNamedEntity(final String name, final String description) {
    super();

    this.name = name;
    this.description = description;
  }

  public String getName() {
    return (name);
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDescription() {
    return (description);
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  //  @Override
  //  public String getId() {
  //    return name;
  //  }
  //
  //  @Override
  //  public void setId(String id) {
  //    this.name = id;
  //  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractNamedEntity that = (AbstractNamedEntity) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }
}
