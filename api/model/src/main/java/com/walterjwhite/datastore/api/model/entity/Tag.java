package com.walterjwhite.datastore.api.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
// @PersistenceCapable
@Entity
public class Tag extends AbstractNamedEntity {

  @ManyToOne @JoinColumn protected Tag parent;

  @OneToMany(mappedBy = "parent")
  protected Set<Tag> children;

  @OneToMany protected List<EntityReference> entityReferences = new ArrayList();

  public Tag(String name, Tag parent) {
    super(name);
    this.parent = parent;
  }

  public Tag(String name) {
    super(name);
  }
}
