package com.walterjwhite.infrastructure.datastore.modules.jdbc;

import com.google.inject.AbstractModule;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.service.ImportSQLService;

public class JDBCImportModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(ImportSQLService.class);
  }
}
