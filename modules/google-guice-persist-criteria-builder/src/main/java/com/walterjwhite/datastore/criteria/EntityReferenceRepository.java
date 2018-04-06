package com.walterjwhite.datastore.criteria;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityReference;
import com.walterjwhite.datastore.api.model.entity.EntityReference_;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityReferenceRepository extends AbstractRepository<EntityReference> {
  private static final Logger LOGGER = LoggerFactory.getLogger(EntityReferenceRepository.class);

  protected final EntityTypeRepository resourceTypeRepository;

  @Inject
  public EntityReferenceRepository(
      EntityManager entityManager,
      CriteriaBuilder criteriaBuilder,
      EntityTypeRepository resourceTypeRepository) {
    super(entityManager, criteriaBuilder, EntityReference.class);

    this.resourceTypeRepository = resourceTypeRepository;
  }

  public EntityReference findOrCreate(AbstractEntity entity) {
    if (entity.getId() == null) entity = persistTransientEntity(entity);

    try {
      return (findOrCreateByEntityTypeAndId(entity.getClass().getName(), entity.getId()));
    } catch (ClassNotFoundException e) {
      throw (new IllegalStateException("Application is mis-configured", e));
    }
  }

  @Transactional
  protected AbstractEntity persistTransientEntity(AbstractEntity entity) {
    return (entityManager.merge(entity));
  }

  public EntityReference findOrCreateByEntityTypeAndId(final String entityTypeName, final String id)
      throws ClassNotFoundException {
    EntityType entityType =
        resourceTypeRepository.findOrCreate(
            (Class<? extends AbstractEntity>) Class.forName(entityTypeName));

    final CriteriaQueryConfiguration<EntityReference> resourceCriteriaQueryConfiguration =
        getCriteriaQuery();

    Predicate resourceTypePredicate =
        criteriaBuilder.equal(
            resourceCriteriaQueryConfiguration.getRoot().get(EntityReference_.entityType),
            entityType);

    Predicate resourceIdPredicate =
        criteriaBuilder.equal(
            resourceCriteriaQueryConfiguration.getRoot().get(EntityReference_.entityId), id);

    resourceCriteriaQueryConfiguration
        .getCriteriaQuery()
        .where(criteriaBuilder.and(resourceTypePredicate, resourceIdPredicate));

    try {
      return (entityManager
          .createQuery(resourceCriteriaQueryConfiguration.getCriteriaQuery())
          .getSingleResult());
    } catch (NoResultException e) {
      return createNewEntityReference(entityType, id);
    }
  }

  @Transactional
  protected EntityReference createNewEntityReference(EntityType entityType, String id) {
    return (persist(new EntityReference(entityType, id)));
  }
}
