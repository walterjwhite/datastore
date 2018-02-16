package com.walterjwhite.datastore.criteria;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityContainerType;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.datastore.api.model.entity.EntityType_;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityTypeRepository extends AbstractRepository<EntityType> {
  private static final Logger LOGGER = LoggerFactory.getLogger(EntityTypeRepository.class);

  @Inject
  public EntityTypeRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super(entityManager, criteriaBuilder, EntityType.class);
  }

  // TODO: periodically retrieve jobs and throw them into the executor service.
  public EntityType findByName(final String entityTypeName) {
    final CriteriaQueryConfiguration<EntityType> resourceTypeCriteriaQueryConfiguration =
        getCriteriaQuery();

    Predicate entityTypeNameCondition =
        criteriaBuilder.equal(
            resourceTypeCriteriaQueryConfiguration.getRoot().get(EntityType_.name), entityTypeName);

    resourceTypeCriteriaQueryConfiguration.getCriteriaQuery().where(entityTypeNameCondition);

    return (entityManager
        .createQuery(resourceTypeCriteriaQueryConfiguration.getCriteriaQuery())
        .getSingleResult());
  }

  // TODO: periodically retrieve jobs and throw them into the executor service.
  public EntityType findOrCreate(Class<? extends AbstractEntity> entityClassType) {
    final CriteriaQueryConfiguration<EntityType> resourceTypeCriteriaQueryConfiguration =
        getCriteriaQuery();

    Predicate entityTypeCondition =
        criteriaBuilder.equal(
            resourceTypeCriteriaQueryConfiguration.getRoot().get(EntityType_.name),
            entityClassType.getName());
    Predicate entityContainerTypeCondition =
        criteriaBuilder.equal(
            resourceTypeCriteriaQueryConfiguration.getRoot().get(EntityType_.entityContainerType),
            EntityContainerType.Database);

    resourceTypeCriteriaQueryConfiguration
        .getCriteriaQuery()
        .where(criteriaBuilder.and(entityTypeCondition, entityContainerTypeCondition));

    try {
      return (entityManager
          .createQuery(resourceTypeCriteriaQueryConfiguration.getCriteriaQuery())
          .getSingleResult());
    } catch (NoResultException e) {
      //      LOGGER.info("tx active:" + entityManager.getTransaction().isActive());
      return (persist(new EntityType(entityClassType.getName(), EntityContainerType.Database)));
    }
  }

  public EntityType findOrCreate(String filename) {
    final CriteriaQueryConfiguration<EntityType> resourceTypeCriteriaQueryConfiguration =
        getCriteriaQuery();

    Predicate entityTypeCondition =
        criteriaBuilder.equal(
            resourceTypeCriteriaQueryConfiguration.getRoot().get(EntityType_.name),
            /*file.getAbsolutePath()*/ filename);
    Predicate entityContainerTypeCondition =
        criteriaBuilder.equal(
            resourceTypeCriteriaQueryConfiguration.getRoot().get(EntityType_.entityContainerType),
            EntityContainerType.File);

    resourceTypeCriteriaQueryConfiguration
        .getCriteriaQuery()
        .where(criteriaBuilder.and(entityTypeCondition, entityContainerTypeCondition));

    try {
      return (entityManager
          .createQuery(resourceTypeCriteriaQueryConfiguration.getCriteriaQuery())
          .getSingleResult());
    } catch (NoResultException e) {
      //      LOGGER.info("tx active:" + entityManager.getTransaction().isActive());
      return (persist(
          new EntityType(/*file.getAbsolutePath()*/ filename, EntityContainerType.File)));
    }
  }
}
