package com.walterjwhite.infrastructure.datastore.modules.jdbc.run.providers.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.run.service.RunSQLService;
import com.walterjwhite.serialization.modules.snakeyaml.providers.guice.SnakeyamlSerializationServiceModule;

public class JDBCRunModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(RunSQLService.class);

    install(new SnakeyamlSerializationServiceModule());
  }
}
