package com.walterjwhite.datastore.examples.querydsl.service;

import com.google.inject.persist.Transactional;
import com.walterjwhite.datastore.examples.querydsl.model.CustomerOrder;
import com.walterjwhite.datastore.examples.querydsl.model.Pizza;
import javax.inject.Inject;

public class DefaultOrderService {
  protected final PizzaService pizzaService;

  @Inject
  public DefaultOrderService(PizzaService pizzaService) {
    super();
    this.pizzaService = pizzaService;
  }

  @Transactional
  public CustomerOrder order(Pizza pizza, int orderId) {
    CustomerOrder customerOrder = new CustomerOrder();
    customerOrder.setId(orderId);

    pizza.setCustomerOrder(customerOrder);
    customerOrder.getPizzas().add(pizza);

    pizzaService.save(customerOrder);

    return (customerOrder);
  }
}
