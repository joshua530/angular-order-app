package com.foods.angularwebapp.utils;

public class OrderUpdateRequest {
  private String token;
  private Integer orderId;
  private OrderStatus status;

  public OrderUpdateRequest(String token, Integer orderId, OrderStatus status) {
    this.token = token;
    this.orderId = orderId;
    this.status = status;
  }

  public OrderUpdateRequest() {
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "OrderUpdateRequest [orderId=" + orderId + ", status=" + status + ", token=" + token + "]";
  }
}
