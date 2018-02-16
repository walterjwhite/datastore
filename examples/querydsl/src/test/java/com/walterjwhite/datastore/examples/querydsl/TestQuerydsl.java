package com.walterjwhite.datastore.examples.querydsl;

import com.walterjwhite.datastore.examples.querydsl.service.PizzaQuerydslService;
import org.junit.Test;

public class TestQuerydsl extends AbstractPerformanceTest {
  public TestQuerydsl() {
    super(PizzaQuerydslService.class, 20);
  }

  @Test
  public void testGet() {
    doPerformanceTest();
  }

  @Test
  public void testGetByTopping() {
    doPerformanceTestGetByToppings();
  }
}
