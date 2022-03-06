package com.foods.angularwebapp.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.foods.angularwebapp.utils.OrderStatus;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "order_generator")
  @SequenceGenerator(name = "order_sequence", allocationSize = 1)
  private Integer id;

  @Column(nullable = false)
  private String timePlaced;

  @Column(nullable = false)
  private Integer productQuantity;

  public Integer getProductQuantity() {
    return productQuantity;
  }

  public void setProductQuantity(Integer productQuantity) {
    this.productQuantity = productQuantity;
  }

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @ManyToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
  private User user;

  private String userIdStr;

  public String getUserIdStr() {
    return userIdStr;
  }

  public void setUserIdStr(String userId) {
    this.userIdStr = userId;
  }

  private Integer productIdNum;

  public Integer getProductIdNum() {
    return productIdNum;
  }

  public void setProductIdNum(Integer productId) {
    this.productIdNum = productId;
  }

  @ManyToOne(targetEntity = Product.class, cascade = CascadeType.MERGE)
  private Product product;

  public Order(String timePlaced, Integer productQuantity, OrderStatus status, String userId, Integer productId) {
    this.timePlaced = timePlaced;
    this.productQuantity = productQuantity;
    this.status = status;
    this.userIdStr = userId;
    this.productIdNum = productId;
  }

  public Order(String timePlaced, Integer quantity, OrderStatus status, User user, Product product) {
    this.timePlaced = timePlaced;
    this.productQuantity = quantity;
    this.status = status;
    this.user = user;
    this.product = product;
  }

  public Order(Integer id, String timePlaced, Integer quantity, OrderStatus status, Product product) {
    this.id = id;
    this.timePlaced = timePlaced;
    this.productQuantity = quantity;
    this.status = status;
    this.product = product;
  }
  
  public Order(Integer id, String timePlaced, Integer quantity, OrderStatus status, Product product, String userId) {
    this.id = id;
    this.timePlaced = timePlaced;
    this.productQuantity = quantity;
    this.status = status;
    this.product = product;
    this.userIdStr = userId;
  }

  public Order() {
  }

  public Integer getId() {
    return id;
  }

  public String getTimePlaced() {
    return timePlaced;
  }

  public void setTimePlaced(String timePlaced) {
    this.timePlaced = timePlaced;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", product=" + product + ", productIdNum=" + productIdNum + ", productQuantity="
        + productQuantity + ", status=" + status + ", timePlaced=" + timePlaced + ", user=" + user + ", userIdStr="
        + userIdStr + "]";
  }
}
