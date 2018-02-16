package com.walterjwhite.datastore.examples.querydsl;

import com.walterjwhite.datastore.examples.querydsl.service.PizzaCriteriaService;
import org.junit.Test;

public class TestCriteria extends AbstractPerformanceTest {
  public TestCriteria() {
    super(PizzaCriteriaService.class, 30);
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
