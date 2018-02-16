package com.walterjwhite.datastore;

import com.google.inject.AbstractModule;

public class GoogleGuicePersistModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(GoogleGuicePersistService.class).asEagerSingleton();

    //    install(new JpaPersistModule());
  }
}
