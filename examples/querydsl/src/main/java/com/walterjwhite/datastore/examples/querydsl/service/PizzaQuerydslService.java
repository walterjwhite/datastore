package com.walterjwhite.datastore.examples.querydsl.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walterjwhite.datastore.examples.querydsl.model.*;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

public class PizzaQuerydslService implements PizzaService {
  protected final Provider<EntityManager> entityManagerProvider;

  @Inject
  public PizzaQuerydslService(Provider<EntityManager> entityManagerProvider) {
    super();
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public CustomerOrder save(CustomerOrder customerOrder) {
    entityManagerProvider.get().persist(customerOrder);

    return customerOrder;
  }

  public CustomerOrder get(int orderId) {
    EntityManager entityManager = entityManagerProvider.get();
    entityManager.clear();

    //    JPAQuery<?> query = new JPAQuery<Void>(entityManager);
    JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager /*Provider.get()*/);

    QCustomerOrder qCustomerOrder = QCustomerOrder.customerOrder;
    CustomerOrder retrievedCustomerOrder =
        jpaQueryFactory.selectFrom(qCustomerOrder).where(qCustomerOrder.id.eq(orderId)).fetchOne();
    //        System.out.println("CustomerOrder 1:" + retrievedCustomerOrder);

    return (retrievedCustomerOrder);
  }

  public List<Pizza> get(Topping topping) {
    EntityManager entityManager = entityManagerProvider.get();
    entityManager.clear();

    //    JPAQuery<?> query = new JPAQuery<Void>(entityManager);
    JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager /*Provider.get()*/);

    QCustomerOrder qCustomerOrder = QCustomerOrder.customerOrder;
    QPizza qPizza = QPizza.pizza;
    return jpaQueryFactory.selectFrom(qPizza).where(qPizza.toppings.contains(topping)).fetch();
  }
}
