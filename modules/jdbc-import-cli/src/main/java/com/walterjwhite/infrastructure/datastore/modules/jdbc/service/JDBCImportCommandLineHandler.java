package com.walterjwhite.infrastructure.datastore.modules.jdbc.service;

import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import javax.inject.Inject;

public class JDBCImportCommandLineHandler extends AbstractCommandLineHandler {
  @Inject
  public JDBCImportCommandLineHandler(int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  public void run(String... arguments) throws Exception {}

  @Override
  protected void onShutdown() throws Exception {}
}
