package com.walterjwhite.infrastructure.datastore.modules.criteria.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.query.AbstractCountQuery;
import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

// TODO: optionally support passing in another query configuration so that the count for that query
// can be determined
public class CountQueryBuilder<EntityType extends AbstractEntity>
    extends JpaCriteriaQueryBuilder<EntityType, Long, AbstractCountQuery<EntityType>> {
  @Override
  protected Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<EntityType, Long> criteriaQueryConfiguration,
      AbstractCountQuery<EntityType> entityTypeAbstractCountQueryConfiguration) {
    // criteriaQuery.select(criteriaBuilder.count(root));
    // let subclass define the criteria / predicate

    return null;
  }
}
