package com.walterjwhite.datastore.api.model.entity;

// import com.walterjwhite.datastore.api.annotation.Version;
import com.walterjwhite.datastore.api.annotation.PrimaryKey;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** TODO: provide a base class where an ID is a UUID where the first few bytes are from the node. */
// @Version(strategy = VersionStrategy.VERSION_NUMBER)
// @PersistenceAware
@Getter
@Setter
@ToString(doNotUseGetters = true)
@Embeddable
@MappedSuperclass
public abstract class AbstractEntity implements Serializable /*, StoreCallback*/ {
  @PrimaryKey @Id protected Integer id;

  @Version protected int version;

  /**
   * This serves to log the hashCode as well as allow the id generation strategy to be overridden.
   */
  // TODO: confirm what should happen if one of the values in the PK changes, this value should also
  // change ...
  @PrePersist
  public void onPreCreate() {
    if (id == null) id = hashCode();
  }
}
