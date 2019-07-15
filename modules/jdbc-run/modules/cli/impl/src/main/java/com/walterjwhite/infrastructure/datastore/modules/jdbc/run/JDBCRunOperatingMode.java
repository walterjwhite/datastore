package com.walterjwhite.infrastructure.datastore.modules.jdbc.run;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum JDBCRunOperatingMode implements OperatingMode {
  @DefaultValue
  Default(JDBCRunCommandLineHandler.class);

  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  JDBCRunOperatingMode(Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
