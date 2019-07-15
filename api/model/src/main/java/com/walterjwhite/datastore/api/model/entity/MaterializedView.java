package com.walterjwhite.datastore.api.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Deprecated
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@Entity
public class MaterializedView extends AbstractNamedEntity {
  @ManyToMany @JoinTable protected Set<Tag> tags;
  @ManyToMany @JoinTable protected List<EntityReference> records;

  @Column protected LocalDateTime dateUpdated;
}
