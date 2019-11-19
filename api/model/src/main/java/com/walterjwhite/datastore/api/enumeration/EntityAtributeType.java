package com.walterjwhite.datastore.api.enumeration;

import com.walterjwhite.datastore.api.model.entity.EntityType;
import java.lang.reflect.InvocationTargetException;
import javax.naming.ConfigurationException;

public enum EntityAtributeType {
  EnumAttribute() {
    protected boolean is(com.walterjwhite.datastore.api.model.entity.EntityType entityType) {
      return entityType.getName().contains(EntityAttribute.TYPE_SEPARATOR_STRING);
    }

    protected <ActualEntityType> ActualEntityType doGet(EntityType entityType)
        throws ConfigurationException {
      try {
        final String enumName = getEnumName(entityType.getName());

        final String enumType = getEnumType(entityType.getName());
        final Class enumClass = Class.forName(enumType);
        final Enum e = Enum.valueOf(enumClass, enumName);

        return (ActualEntityType) e;
      } catch (ClassNotFoundException e) {
        throw buildException(e, "Enum", entityType);
      }
    }

    private String getEnumName(final String entityTypeName) {
      return entityTypeName.split(EntityAttribute.TYPE_SEPARATOR_STRING)[1];
    }

    private String getEnumName(final Enum e) {
      return e.getClass().getName() + EntityAttribute.TYPE_SEPARATOR + e.name();
    }

    private String getEnumType(final String entityTypeName) {
      return entityTypeName.split(EntityAttribute.TYPE_SEPARATOR_STRING)[0];
    }
  },
  ClassAttribute() {
    protected boolean is(com.walterjwhite.datastore.api.model.entity.EntityType entityType) {
      return !entityType.getName().contains(EntityAttribute.TYPE_SEPARATOR_STRING);
    }

    protected <ActualEntityType> ActualEntityType doGet(EntityType entityType)
        throws ConfigurationException {
      try {
        final Class entityTypeClass = Class.forName(entityType.getName());
        return (ActualEntityType) entityTypeClass.getDeclaredConstructor().newInstance();
      } catch (ClassNotFoundException
          | IllegalAccessException
          | InstantiationException
          | NoSuchMethodException
          | InvocationTargetException e) {
        throw buildException(e, "Class", entityType);
      }
    }
  };

  public <ActualEntityType> ActualEntityType get(
      com.walterjwhite.datastore.api.model.entity.EntityType entityType)
      throws ConfigurationException {
    for (EntityAtributeType entityAtributeType : values()) {
      if (entityAtributeType.is(entityType)) return entityAtributeType.doGet(entityType);
    }

    throw new ConfigurationException(entityType + " is not currently supported?");
  }

  protected abstract <ActualEntityType> ActualEntityType doGet(
      com.walterjwhite.datastore.api.model.entity.EntityType entityType)
      throws ConfigurationException;

  protected abstract boolean is(com.walterjwhite.datastore.api.model.entity.EntityType entityType);

  private static ConfigurationException buildException(
      final Exception e, final String entityTypeType, final EntityType entityType) {
    return new ConfigurationException(
        String.format(
            "Application is incorrectly configured for processing entity types for %s (%s)",
            entityTypeType, entityType.getName()));
  }
}
