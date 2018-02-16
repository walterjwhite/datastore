package com.walterjwhite.datastore.examples.querydsl.service;

import com.walterjwhite.datastore.examples.querydsl.model.CustomerOrder;
import com.walterjwhite.datastore.examples.querydsl.model.Pizza;
import com.walterjwhite.datastore.examples.querydsl.model.Topping;
import java.util.List;

public interface PizzaService {
  CustomerOrder save(CustomerOrder customerOrder);

  CustomerOrder get(int customerOrderId);

  List<Pizza> get(Topping topping);
}
