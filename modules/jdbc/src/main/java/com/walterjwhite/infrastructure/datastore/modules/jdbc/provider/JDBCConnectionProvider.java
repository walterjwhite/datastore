package com.walterjwhite.infrastructure.datastore.modules.jdbc.provider;

import com.walterjwhite.infrastructure.datastore.modules.jdbc.model.JDBCConfiguration;
import java.sql.Connection;

public class JDBCConnectionProvider {
  public static Connection get(JDBCConfiguration jdbcConfiguration) {
    jdbcConfiguration.getDriverClassName();
    jdbcConfiguration.getJdbcUrl();
    jdbcConfiguration.getUsername();
    jdbcConfiguration.getPassword();
    return null;
  }
}
