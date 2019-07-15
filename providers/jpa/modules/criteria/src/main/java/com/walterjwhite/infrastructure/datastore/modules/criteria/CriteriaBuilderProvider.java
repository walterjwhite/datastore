package com.walterjwhite.infrastructure.datastore.modules.criteria;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

/** Provides an instance of CriteriaBuilder so that we may build a query easily. */
public class CriteriaBuilderProvider implements Provider<CriteriaBuilder> {
  protected final EntityManager entityManager;

  @Inject
  public CriteriaBuilderProvider(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public CriteriaBuilder get() {
    return (entityManager.getCriteriaBuilder());
  }
}
