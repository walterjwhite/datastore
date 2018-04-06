package com.walterjwhite.infrastructure.datastore.modules.jdbc.model;

import java.util.HashMap;
import java.util.Map;

// TODO: make this extend an entity so we can persist the configuration
public class JDBCConfiguration {

  protected String username;
  protected String password;

  protected String jdbcUrl;
  protected String driverClassName;
  protected String driverPath;

  protected Map<String, String> properties = new HashMap<>();

  public JDBCConfiguration(
      String username, String password, String jdbcUrl, String driverClassName, String driverPath) {
    this.username = username;
    this.password = password;
    this.jdbcUrl = jdbcUrl;
    this.driverClassName = driverClassName;
    this.driverPath = driverPath;
  }

  public String getDriverPath() {
    return driverPath;
  }

  public void setDriverPath(String driverPath) {
    this.driverPath = driverPath;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getJdbcUrl() {
    return jdbcUrl;
  }

  public void setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  public String getDriverClassName() {
    return driverClassName;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }
}
