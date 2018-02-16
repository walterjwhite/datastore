package com.walterjwhite.datastore.examples.querydsl.service;

// import static com.querydsl.collections.CollQueryFactory.*;

import com.walterjwhite.datastore.examples.querydsl.model.CustomerOrder;
import com.walterjwhite.datastore.examples.querydsl.model.Pizza;
import com.walterjwhite.datastore.examples.querydsl.model.Topping;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

public class PizzaJPAService implements PizzaService {

  protected final Provider<EntityManager> entityManagerProvider;

  @Inject
  public PizzaJPAService(Provider<EntityManager> entityManagerProvider) {
    super();
    this.entityManagerProvider = entityManagerProvider;
  }

  public CustomerOrder save(CustomerOrder customerOrder) {
    entityManagerProvider.get().persist(customerOrder);

    return customerOrder;
  }

  public CustomerOrder get(int orderId) {
    return entityManagerProvider.get().find(CustomerOrder.class, orderId);
  }

  @Override
  public List<Pizza> get(Topping topping) {
    return null;
  }
}
