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
@Table(name = "products", indexes = @Index(columnList = "name"))
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_generator")
  @SequenceGenerator(name = "product_sequence", allocationSize = 1)
  private int id;

  @Column(unique = true, nullable = false)
  private String name;
  @Column(nullable = false)
  private double price;
  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  private String imageUrl;
  @Column(nullable = false)
  private String nutritionInfo;

  public Product() {
  }

  public Product(String name, double price, String description, String imageUrl, String nutritionInfo) {
    this.name = name;
    this.price = price;
    this.description = description;
    this.imageUrl = imageUrl;
    this.nutritionInfo = nutritionInfo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getNutritionInfo() {
    return nutritionInfo;
  }

  public void setNutritionInfo(String nutritionInfo) {
    this.nutritionInfo = nutritionInfo;
  }
}
