package com.foods.angularwebapp.utils;

import com.foods.angularwebapp.models.Order;

/** Utility class for processing order */
public class OrderAndAuthToken {
  private Order order;
  private AuthToken token;

  public OrderAndAuthToken() {
  }

  public OrderAndAuthToken(Order order, AuthToken token) {
    this.order = order;
    this.token = token;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public AuthToken getToken() {
    return token;
  }

  public void setToken(AuthToken token) {
    this.token = token;
  }
}
