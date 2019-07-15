package com.walterjwhite.infrastructure.datastore.modules.jdbc.run.providers.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;

public class JDBCGuiceCLIModule extends AbstractModule implements GuiceApplicationModule {
  @Override
  protected void configure() {
    // install(new GoogleGuicePersistModule());

    // TODO: install this module inside the google guice persist module whilst keeping one set of
    // properties
    // install(new JpaPersistModule(propertyManager.get(JPAUnit.class)));
    install(new JDBCRunModule());
  }
}
