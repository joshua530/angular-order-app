package com.foods.angularwebapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users", indexes = @Index(columnList = "username"))
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_generator")
  @SequenceGenerator(name = "user_sequence", allocationSize = 1)
  private int id;

  private String strId;

  @Column(nullable = false)
  private String username;
  private boolean isAdmin = false;

  private String password;

  public User(String username, boolean isAdmin) {
    this.username = username;
    this.isAdmin = isAdmin;
  }

  public User() {
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStrId() {
    return strId;
  }

  public void setStrId(String strId) {
    this.strId = strId;
  }
}
