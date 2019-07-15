package com.walterjwhite.datastore.api.repository;

import com.google.inject.AbstractModule;
import com.walterjwhite.datastore.DefaultQueryBuilderResolver;
import com.walterjwhite.datastore.modules.jpa.JpaRepository;
import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaBuilderProvider;
import javax.persistence.criteria.CriteriaBuilder;

public class CriteriaBuilderModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(CriteriaBuilder.class).toProvider(CriteriaBuilderProvider.class);

    bind(Repository.class).to(JpaRepository.class);
    bind(QueryBuilderResolver.class).to(DefaultQueryBuilderResolver.class);

    // bind(Repository.class).to(CriteriaRepository.class);
  }
}
