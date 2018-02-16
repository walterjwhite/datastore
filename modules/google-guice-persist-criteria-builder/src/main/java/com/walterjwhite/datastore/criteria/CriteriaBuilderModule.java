package com.walterjwhite.datastore.criteria;

import com.google.inject.AbstractModule;
import javax.persistence.criteria.CriteriaBuilder;

public class CriteriaBuilderModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(CriteriaBuilder.class).toProvider(CriteriaBuilderProvider.class);
  }
}
