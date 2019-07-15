package com.walterjwhite.infrastructure.datastore.modules.criteria;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class FindAbstractNamedEntityByNameQueryBuilder {
  private static <EntityType extends AbstractNamedEntity> Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<EntityType, EntityType> criteriaQueryConfiguration,
      final String name) {
    return criteriaBuilder.equal(
        criteriaQueryConfiguration.getRoot().get(AbstractNamedEntity_.name), name);
  }
}
