package com.walterjwhite.datastore.api.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Tag extends AbstractNamedEntity {

  @ManyToOne @JoinColumn protected Tag parent;

  @OneToMany(cascade = CascadeType.ALL)
  protected Set<Tag> children;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable
  protected List<EntityReference> entityReferences = new ArrayList();

  public Tag(String name, Tag parent) {
    super(name);
    this.parent = parent;
  }

  public Tag(String name) {
    super(name);
  }

  public Tag() {
    super();
  }

  public Tag getParent() {
    return parent;
  }

  public void setParent(Tag parent) {
    this.parent = parent;
  }

  public Set<Tag> getChildren() {
    return children;
  }

  public void setChildren(Set<Tag> children) {
    this.children = children;
  }

  public List<EntityReference> getEntityReferences() {
    return entityReferences;
  }

  public void setEntityReferences(List<EntityReference> entityReferences) {
    this.entityReferences = entityReferences;
  }
}
