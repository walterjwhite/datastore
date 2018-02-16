package com.walterjwhite.datastore.examples.querydsl.service;

import com.walterjwhite.datastore.examples.querydsl.model.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PizzaCriteriaService implements PizzaService {

  protected final Provider<EntityManager> entityManagerProvider;

  @Inject
  public PizzaCriteriaService(Provider<EntityManager> entityManagerProvider) {
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
    //    System.out.println("EM:" + entityManager);

    CriteriaQuery<CustomerOrder> criteriaQuery =
        entityManager /*Provider.get()*/.getCriteriaBuilder().createQuery(CustomerOrder.class);
    Root<CustomerOrder> root = criteriaQuery.from(CustomerOrder.class);

    return (entityManager /*Provider
                          .get()*/
        .createQuery(criteriaQuery)
        .setFirstResult(0)
        .setMaxResults(1)
        .getSingleResult());
  }

  public List<Pizza> get(Topping topping) {
    EntityManager entityManager = entityManagerProvider.get();
    entityManager.clear();

    CriteriaQuery<Pizza> criteriaQuery =
        entityManager /*Provider.get()*/.getCriteriaBuilder().createQuery(Pizza.class);
    Root<Pizza> root = criteriaQuery.from(Pizza.class);
    final Set<Topping> toppings = new HashSet<>();
    toppings.add(topping);
    criteriaQuery.where(root.get(Pizza_.toppings).in(toppings));

    return (entityManager /*Provider
                          .get()*/
        .createQuery(criteriaQuery)
        .getResultList());
  }
}
