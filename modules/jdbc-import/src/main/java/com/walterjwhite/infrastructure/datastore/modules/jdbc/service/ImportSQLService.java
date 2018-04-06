package com.walterjwhite.infrastructure.datastore.modules.jdbc.service;

import com.walterjwhite.datastore.PersistenceStartupService;
import com.walterjwhite.google.guice.property.annotation.Property;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.annotation.*;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.model.JDBCConfiguration;
import com.walterjwhite.infrastructure.datastore.modules.jdbc.provider.JDBCAwareContainer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportSQLService implements PersistenceStartupService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ImportSQLService.class);

  protected final File[] sourceFiles;
  protected final JDBCAwareContainer jdbcAwareContainer;

  public ImportSQLService(
      @Property(ImportSQLFile.class) String importSQLFileString,
      @Property(JDBCDriverClassName.class) String jdbcDriverClassname,
      @Property(JDBCUrl.class) String jdbcUrl,
      @Property(JDBCUsername.class) String jdbcUsername,
      @Property(JDBCPassword.class) String jdbcPassword,
      @Property(JDBCDriverPath.class) String jdbcDriverPath) {

    this.sourceFiles = getSourceFiles(importSQLFileString.split(","));

    jdbcAwareContainer =
        new JDBCAwareContainer(
            new JDBCConfiguration(
                jdbcUsername, jdbcPassword, jdbcUrl, jdbcDriverClassname, jdbcDriverPath));
  }

  protected final File[] getSourceFiles(final String[] importSQLFiles) {
    final File[] parsedSourceFiles = new File[importSQLFiles.length];
    for (int i = 0; i < importSQLFiles.length; i++)
      parsedSourceFiles[i] = new File(importSQLFiles[i]);

    return parsedSourceFiles;
  }

  public void run() {
    for (final File sourceFile : sourceFiles) {
      try (final FileInputStream fis = new FileInputStream(sourceFile)) {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
          String line;
          while ((line = reader.readLine()) != null) {
            try (final CallableStatement callableStatement =
                jdbcAwareContainer.getConnection().prepareCall(line)) {
              callableStatement.execute();
            }
          }
        }
      } catch (Exception e) {
        throw (new RuntimeException("Error processing file:" + sourceFile));
      }
    }
  }

  public void close() {
    try {
      jdbcAwareContainer.getConnection().commit();
    } catch (SQLException e) {
      LOGGER.warn("Error commiting tx", e);
    }

    try {
      jdbcAwareContainer.getConnection().close();
    } catch (SQLException e) {
      LOGGER.warn("Error closing connection", e);
    }
  }
}
