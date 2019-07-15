package com.walterjwhite.datastore.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
// @Data
@ToString(doNotUseGetters = true)
@Getter
// @ToString(doNotUseGetters=true)(callSuper = true)
public abstract class AbstractQuery<EntityType extends AbstractEntity, ResultType extends Object>
    extends AbstractEntity implements QueryConfiguration<EntityType, ResultType> {
  protected final int offset;
  protected final int recordCount;
  protected final Class<EntityType> entityTypeClass;
  protected final Class<ResultType> resultTypeClass;
  protected final boolean construct;
}
