package com.walterjwhite.infrastructure.datastore.modules.jdbc.run;

import com.walterjwhite.infrastructure.datastore.modules.jdbc.run.service.RunSQLService;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class JDBCRunCommandLineHandler extends AbstractCommandLineHandler {
  protected final RunSQLService runSQLService;

  @Inject
  public JDBCRunCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) final int shutdownTimeoutInSeconds,
      RunSQLService runSQLService) {
    super(shutdownTimeoutInSeconds);
    this.runSQLService = runSQLService;
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    runSQLService.run();
  }
}
