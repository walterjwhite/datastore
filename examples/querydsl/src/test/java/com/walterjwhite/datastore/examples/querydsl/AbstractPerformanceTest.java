package com.walterjwhite.datastore.examples.querydsl;

import com.google.inject.Injector;
import com.mysema.commons.lang.Assert;
import com.walterjwhite.datastore.examples.querydsl.model.CustomerOrder;
import com.walterjwhite.datastore.examples.querydsl.model.Pizza;
import com.walterjwhite.datastore.examples.querydsl.model.Topping;
import com.walterjwhite.datastore.examples.querydsl.service.DefaultOrderService;
import com.walterjwhite.datastore.examples.querydsl.service.PizzaService;
import com.walterjwhite.google.guice.GuiceHelper;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.Before;

public class AbstractPerformanceTest {
  protected final Class<? extends PizzaService> pizzaServiceClass;
  protected final int orderId;
  protected final int iterations;

  protected Injector injector;

  protected PizzaService pizzaService;
  protected DefaultOrderService orderService;

  public AbstractPerformanceTest(Class<? extends PizzaService> pizzaServiceClass, int orderId) {
    this(pizzaServiceClass, orderId, 100000);
  }

  public AbstractPerformanceTest(
      Class<? extends PizzaService> pizzaServiceClass, int orderId, int iterations) {
    this.pizzaServiceClass = pizzaServiceClass;
    this.orderId = orderId;
    this.iterations = iterations;
  }

  @Before
  public void onBefore() throws Exception {
    GuiceHelper.addModules(new QuerydslModule(pizzaServiceClass));
    GuiceHelper.setup();

    injector = GuiceHelper.getGuiceInjector();
    pizzaService = injector.getInstance(PizzaService.class);
    orderService = injector.getInstance(DefaultOrderService.class);
  }

  @After
  public void onAfter() {
    GuiceHelper.stop();
  }

  protected void placeOrder() {
    final Set<Topping> toppings = new HashSet<>();
    toppings.add(Topping.Green_Peppers);
    orderService.order(new Pizza("test-JPA", toppings, null), orderId);
  }

  protected void doPerformanceTest() {
    placeOrder();

    final long start = System.currentTimeMillis();

    final int COUNT = iterations;
    for (int i = 0; i < iterations; i++) {
      CustomerOrder customerOrder = pizzaService.get(orderId);
      Assert.notNull(customerOrder, "Customer Order is null");
      Assert.isTrue(customerOrder.getId() == orderId, "Customer Order id = " + orderId);
    }

    final long end = System.currentTimeMillis();
    System.out.println(pizzaServiceClass + ":runtime:" + (end - start) + " ms");
    System.out.println(
        pizzaServiceClass + ":runtime/tx:" + ((end - start) / (COUNT * 1.0)) + " ms/tx");
  }

  protected void doPerformanceTestGetByToppings() {
    placeOrder();

    final long start = System.currentTimeMillis();

    final int COUNT = iterations;
    for (int i = 0; i < iterations; i++) {
      List<Pizza> pizzas = pizzaService.get(Topping.Green_Peppers);
      Assert.notNull(pizzas, "Expected at least some matching pizzas");
      Assert.isTrue(
          pizzas.get(0).getToppings().contains(Topping.Green_Peppers),
          "Expected green peppers as a topping");
    }

    final long end = System.currentTimeMillis();
    System.out.println(pizzaServiceClass + ":runtime:" + (end - start) + " ms");
    System.out.println(
        pizzaServiceClass + ":runtime/tx:" + ((end - start) / (COUNT * 1.0)) + " ms/tx");
  }
}
