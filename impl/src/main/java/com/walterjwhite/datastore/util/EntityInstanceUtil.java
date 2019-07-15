package com.walterjwhite.datastore.util;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EntityInstanceUtil {
  private EntityInstanceUtil() {}

  public static <EntityType extends AbstractEntity> EntityType instantiateNewObject(
      final Class<EntityType> entityType, final Object... arguments) {
    try {
      return getConstructor(entityType, getConstructorArguments(arguments)).newInstance(arguments);
    } catch (NoSuchMethodException
        | InstantiationException
        | IllegalAccessException
        | InvocationTargetException e) {
      throw new Error(
          "Application is not properly configured for auto-create, ensure arguments are in the same order as the constructor arguments");
    }
  }

  private static Class[] getConstructorArguments(final Object... arguments) {
    final Class[] constructorArguments = new Class[arguments.length];
    for (int i = 0; i < constructorArguments.length; i++)
      constructorArguments[i] = arguments[i].getClass();

    return constructorArguments;
  }

  private static <EntityType extends AbstractEntity> Constructor<EntityType> getConstructor(
      final Class<EntityType> entityType, final Class[] constructorArguments)
      throws NoSuchMethodException {
    return entityType.getConstructor(constructorArguments);
  }
}
