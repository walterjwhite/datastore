package com.walterjwhite.datastore.examples.querydsl.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Pizza implements Serializable {
  @Id protected String name;

  @Enumerated(EnumType.STRING)
  @ElementCollection
  protected Set<Topping> toppings = new HashSet<>();

  @ManyToOne @JoinColumn protected CustomerOrder customerOrder;

  public Pizza(String name, Set<Topping> toppings, CustomerOrder customerOrder) {
    //    super(name);
    super();
    this.name = name;
    this.toppings = toppings;
    this.customerOrder = customerOrder;
  }

  public Pizza() {
    super();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Topping> getToppings() {
    return toppings;
  }

  public void setToppings(Set<Topping> toppings) {
    this.toppings = toppings;
  }

  public CustomerOrder getCustomerOrder() {
    return customerOrder;
  }

  public void setCustomerOrder(CustomerOrder customerOrder) {
    this.customerOrder = customerOrder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pizza pizza = (Pizza) o;
    return Objects.equals(name, pizza.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }
}
