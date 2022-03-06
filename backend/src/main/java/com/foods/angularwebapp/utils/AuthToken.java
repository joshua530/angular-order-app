package com.foods.angularwebapp.utils;

public class AuthToken {
  private String token;

  public AuthToken() {
    this.token = "invalid";
  }

  public AuthToken(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
