package com.walterjwhite.datastore;

import com.google.inject.persist.PersistService;
import com.walterjwhite.datastore.api.PersistenceShutdownService;
import com.walterjwhite.datastore.api.PersistenceStartupService;
import com.walterjwhite.logging.annotation.LogStackTrace;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.reflections.Reflections;

@Singleton
public class GoogleGuicePersistService
    implements PersistenceStartupService, PersistenceShutdownService {
  protected final PersistService persistService;
  protected final Reflections reflections;

  // @see
  // https://github.com/nuzayats/guice-persist-two-pu/blob/master/src/test/java/guice/MyModuleTest.java
  @Inject
  public GoogleGuicePersistService(PersistService persistService, Reflections reflections) {
    super();
    this.persistService = persistService;
    this.reflections = reflections;

    doStart();
  }

  protected void doStart() {
    persistService.start();
  }

  @Override
  public void start() throws Exception {
    // persistService.start();
  }

  @Override
  public void stop() throws Exception {
    doShutdown(persistService);
  }

  protected void doShutdown(PersistService persistService) {
    try {
      persistService.stop();
    } catch (Exception e) {
      doHandleError(e);
    }
  }

  @LogStackTrace
  protected void doHandleError(Exception e) {}
}
