package com.walterjwhite.infrastructure.datastore.modules.jdbc.service;

import com.google.inject.persist.jpa.JpaPersistModule;
import com.walterjwhite.datastore.GoogleGuicePersistModule;
import com.walterjwhite.datastore.JPAUnit;
import com.walterjwhite.google.guice.cli.AbstractCommandLineModule;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.JDBCImportModule;
import org.reflections.Reflections;

public class JDBCImportCommandLineModule extends AbstractCommandLineModule {
  public JDBCImportCommandLineModule(Reflections reflections) {
    super(reflections, JDBCImportOperatingMode.class);
  }

  @Override
  protected void doConfigure() {
    install(new GoogleGuicePersistModule());

    // TODO: install this module inside the google guice persist module whilst keeping one set of
    // properties
    install(new JpaPersistModule(propertyManager.getValue(JPAUnit.class)));
    install(new JDBCImportModule());
  }
}
