package com.foods.angularwebapp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  public Map<String, List<Product>> getAllProducts() {
    List<Product> products = this.service.getAllProducts();
    Map<String, List<Product>> categorized = new HashMap<>();
    List<Product> featured = new ArrayList<Product>();
    List<Product> hotDeals = new ArrayList<Product>();
    List<Product> others = new ArrayList<Product>();

    products.forEach(p -> {
      if (p.getIsFeatured())
        featured.add(p);
      else if (p.getIsHotDeal())
        hotDeals.add(p);
      else
        others.add(p);
    });

    categorized.put("featured", featured);
    categorized.put("hotDeals", hotDeals);
    categorized.put("others", others);

    return categorized;
  }

  @GetMapping(path = "{product-id}")
  public Product getProduct(@PathVariable(name = "product-id") int productId) {
    return this.service.getProduct(productId);
  }
}
