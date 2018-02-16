package com.walterjwhite.datastore.criteria;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CriteriaQueryConfiguration<EntityType extends AbstractEntity> {
  protected final CriteriaQuery<EntityType> criteriaQuery;
  protected final Root<EntityType> root;

  public CriteriaQueryConfiguration(
      CriteriaQuery<EntityType> criteriaQuery, Root<EntityType> root) {
    super();
    this.criteriaQuery = criteriaQuery;
    this.root = root;
  }

  public CriteriaQuery<EntityType> getCriteriaQuery() {
    return criteriaQuery;
  }

  public Root<EntityType> getRoot() {
    return root;
  }
}
