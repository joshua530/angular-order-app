package com.foods.angularwebapp.controllers;

import java.util.List;

import com.foods.angularwebapp.models.Product;
import com.foods.angularwebapp.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {
  private ProductService service;

  @Autowired
  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return this.service.getAllProducts();
  }

  @GetMapping(path = "{product-id}")
  public Product getProduct(@PathVariable(name = "product-id") int productId) {
    return this.service.getProduct(productId);
  }
}
