package com.walterjwhite.datastore;

import com.google.common.util.concurrent.AbstractIdleService;
import com.google.inject.persist.PersistService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class GoogleGuicePersistService extends AbstractIdleService {
  private static final Logger LOGGER = LoggerFactory.getLogger(GoogleGuicePersistService.class);

  protected final PersistService persistService;

  @Inject
  public GoogleGuicePersistService(PersistService persistService) {
    super();
    this.persistService = persistService;

    LOGGER.info("starting persistence");
    persistService.start();
  }

  @Override
  protected void startUp() throws Exception {

    //
    //    try {
    //
    //    } catch (Exception e) {
    //      LOGGER.error("Error starting persistence:", e);
    //    }
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
