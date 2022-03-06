package com.foods.angularwebapp.utils;

public enum OrderStatus {
  PENDING("PENDING"),
  PROCESSING("PROCESSING"),
  DELIVERED("DELIVERED");

  private String enumString;

  OrderStatus(String enumString) {
    this.enumString = enumString;
  }

  public String toString() {
    return this.enumString;
  }
}
