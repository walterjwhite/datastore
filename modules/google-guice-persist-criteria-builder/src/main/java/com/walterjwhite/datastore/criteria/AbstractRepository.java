package com.walterjwhite.datastore.criteria;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

public class AbstractRepository<EntityType extends AbstractEntity> extends Repository<EntityType> {
  protected final Class<EntityType> entityTypeClass;

  @Inject
  public AbstractRepository(
      EntityManager entityManager,
      CriteriaBuilder criteriaBuilder,
      Class<EntityType> entityTypeClass) {
    super(entityManager, criteriaBuilder);
    this.entityTypeClass = entityTypeClass;
  }

  public List<EntityType> findAll() {
    return (List<EntityType>) findAll(entityTypeClass);
  }

  public CriteriaQueryConfiguration<EntityType> getCriteriaQuery() {
    return (CriteriaQueryConfiguration<EntityType>) getCriteriaQuery(entityTypeClass);
  }

  public long count(Predicate... conditions) {
    return (count(entityTypeClass, conditions));
  }

  /** Use ES here, it would be more efficient. */
  public void search() {
    //    criteriaBuilder.equal()
    CriteriaQuery<Long> criteriaQuery = null;
    criteriaQuery.getParameters();
    criteriaQuery.getGroupList();
    criteriaQuery.getOrderList();
    criteriaQuery.getGroupRestriction();
    criteriaQuery.getRestriction();
    criteriaQuery.getSelection();

    final Predicate predicate = null;
  }
}
