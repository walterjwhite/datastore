package com.walterjwhite.datastore.examples.querydsl;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.walterjwhite.datastore.GoogleGuicePersistModule;
import com.walterjwhite.datastore.examples.querydsl.service.PizzaService;

public class QuerydslModule extends AbstractModule {
  protected final Class<? extends PizzaService> pizzaServiceClass;

  public QuerydslModule(Class<? extends PizzaService> pizzaServiceClass) {
    this.pizzaServiceClass = pizzaServiceClass;
  }

  @Override
  protected void configure() {
    bind(PizzaService.class).to(pizzaServiceClass);

    install(new JpaPersistModule("defaultJPAUnit"));
    install(new GoogleGuicePersistModule());
  }
}
