package com.walterjwhite.infrastructure.datastore.modules.jdbc.model;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

// TODO: make this extend an entity so we can persist the configuration
@AllArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public class JDBCConfiguration {

  protected String username;
  protected String password;

  protected String jdbcUrl;
  protected String driverClassName;
  protected String driverPath;

  protected final Map<String, String> properties = new HashMap<>();
}
