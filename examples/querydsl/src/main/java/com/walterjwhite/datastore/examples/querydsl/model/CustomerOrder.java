package com.walterjwhite.datastore.examples.querydsl.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class CustomerOrder implements Serializable {
  @Id protected int id;

  @Column protected LocalDateTime dateTime = LocalDateTime.now();

  @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
  protected List<Pizza> pizzas = new ArrayList<>();

  public CustomerOrder() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public List<Pizza> getPizzas() {
    return pizzas;
  }

  public void setPizzas(List<Pizza> pizzas) {
    this.pizzas = pizzas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerOrder that = (CustomerOrder) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
