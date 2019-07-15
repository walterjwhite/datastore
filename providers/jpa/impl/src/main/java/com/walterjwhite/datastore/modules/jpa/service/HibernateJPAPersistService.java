package com.walterjwhite.datastore.modules.jpa.service;

import com.walterjwhite.interruptable.annotation.InterruptableService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.reflections.Reflections;

/** This will conflict with the generic JPA service */
@Singleton
@InterruptableService
public class HibernateJPAPersistService extends AbstractJPAPersistService {
  /** This is required to use the SLF4J logging wrapper. */
  static {
    System.setProperty("org.jboss.logging.provider", "slf4j");
  }

  @Inject
  public HibernateJPAPersistService(Reflections reflections) {
    super(reflections);
  }
}
