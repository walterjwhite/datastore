package com.walterjwhite.datastore.modules;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.walterjwhite.datastore.DefaultQueryBuilderResolver;
import com.walterjwhite.datastore.GoogleGuicePersistService;
import com.walterjwhite.datastore.api.repository.QueryBuilderResolver;
import com.walterjwhite.infrastructure.inject.core.annotation.Primary;

public class GoogleGuicePersistModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new JpaPersistModule(Primary.IDENTIFIER));

    install(new JavaxTransactionalModule());

    bind(QueryBuilderResolver.class).to(DefaultQueryBuilderResolver.class);
    bind(GoogleGuicePersistService.class).asEagerSingleton();

    // TODO: this requires aopalliance in the interceptor
    //    bindInterceptor(Matchers.any(), Matchers.annotatedWith(OnJPAAction.class),
    //            new AuditingRepositoryInterceptor());
  }
}
