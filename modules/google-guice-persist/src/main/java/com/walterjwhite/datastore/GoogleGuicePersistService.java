package com.walterjwhite.datastore;

import com.google.common.util.concurrent.AbstractIdleService;
import com.google.inject.persist.PersistService;
import com.walterjwhite.google.guice.GuiceHelper;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class GoogleGuicePersistService extends AbstractIdleService {
  private static final Logger LOGGER = LoggerFactory.getLogger(GoogleGuicePersistService.class);

  protected final PersistService persistService;
  protected final Reflections reflections;

  @Inject
  public GoogleGuicePersistService(PersistService persistService, Reflections reflections) {
    super();
    this.persistService = persistService;
    this.reflections = reflections;

    LOGGER.info("starting persistence");
    persistService.start();
  }

  @Override
  protected void startUp() throws Exception {
    for (final Class<? extends PersistenceStartupService> persistenceStartupServiceClass :
        reflections.getSubTypesOf(PersistenceStartupService.class)) {
      final PersistenceStartupService persistenceStartupService =
          GuiceHelper.getGuiceInjector().getInstance(persistenceStartupServiceClass);
      persistenceStartupService.run();
    }
  }

  @Override
  protected void shutDown() throws Exception {
    LOGGER.info("stopping persistence");

    try {
      persistService.stop();
    } catch (Exception e) {
      LOGGER.warn("error stopping database", e);
    }
  }
}
