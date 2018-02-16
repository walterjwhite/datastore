package com.walterjwhite.datastore.criteria;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.util.Iterator;
import javax.inject.Provider;
import javax.persistence.criteria.CriteriaQuery;

public class ResultsetIterator<EntityType extends AbstractEntity> implements Iterator<EntityType> {
  protected final CriteriaQuery<EntityType> criteriaQuery;
  protected final int count;
  protected final Provider<Repository> repositoryProvider;

  protected int index = 0;

  public ResultsetIterator(
      CriteriaQuery<EntityType> criteriaQuery, Provider<Repository> repositoryProvider) {
    super();
    this.criteriaQuery = criteriaQuery;
    this.repositoryProvider = repositoryProvider;
    this.count = repositoryProvider.get().count(criteriaQuery);
  }

  @Override
  public boolean hasNext() {
    return index < count - 1;
  }

  @Override
  public EntityType next() {
    return (EntityType) repositoryProvider.get().get(criteriaQuery, index++);
  }
}
