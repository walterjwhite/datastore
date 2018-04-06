package com.walterjwhite.infrastructure.datastore.modules.jdbc.service;

import com.walterjwhite.google.guice.cli.property.OperatingMode;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.google.guice.property.annotation.DefaultValue;

public enum JDBCImportOperatingMode implements OperatingMode {
  @DefaultValue
  Default(JDBCImportCommandLineHandler.class);

  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  JDBCImportOperatingMode(Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
