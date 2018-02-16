package com.walterjwhite.datastore.examples.querydsl;

import com.walterjwhite.datastore.examples.querydsl.service.PizzaJPAService;
import org.junit.Test;

public class TestJPA extends AbstractPerformanceTest {
  public TestJPA() {
    super(PizzaJPAService.class, 10);
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
