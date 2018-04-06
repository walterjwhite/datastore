package com.walterjwhite.datastore.criteria;

import com.walterjwhite.datastore.api.model.entity.EntityType_;
import com.walterjwhite.datastore.api.model.entity.Tag;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

public class TagRepository extends AbstractRepository<Tag> {
  @Inject
  public TagRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super(entityManager, criteriaBuilder, Tag.class);
  }

  public Tag findByName(final String tagName) {
    final CriteriaQueryConfiguration<Tag> resourceTypeCriteriaQueryConfiguration =
        getCriteriaQuery();

    Predicate entityTypeNameCondition =
        criteriaBuilder.equal(
            resourceTypeCriteriaQueryConfiguration.getRoot().get(EntityType_.name), tagName);

    resourceTypeCriteriaQueryConfiguration.getCriteriaQuery().where(entityTypeNameCondition);

    return (entityManager
        .createQuery(resourceTypeCriteriaQueryConfiguration.getCriteriaQuery())
        .getSingleResult());
  }

  public Set<Tag> findOrCreate(final String... tagNames) {
    final Set<Tag> tags = new HashSet<>();

    for (final String tagName : tagNames) tags.add(findOrCreate(tagName));

    return tags;
  }

  @Transactional
  public Tag findOrCreate(final String tagName) {
    try {
      return (findByName(tagName));
    } catch (NoResultException e) {
      return persist(new Tag(tagName));
    }
  }
}
