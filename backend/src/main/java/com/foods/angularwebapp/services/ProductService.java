package com.foods.angularwebapp.services;

import java.util.List;
import java.util.Optional;

import com.foods.angularwebapp.exceptions.ProductNotFoundException;
import com.foods.angularwebapp.models.Product;
import com.foods.angularwebapp.repositories.ProductRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private ProductRepository repository;

  public ProductService(ProductRepository repo) {
    this.repository = repo;
  }

  public List<Product> getAllProducts() {
    return this.repository.findAll();
  }

  public Product getProduct(int id) {
    Optional<Product> fetchedProduct = this.repository.findById(id);
    if (fetchedProduct.isEmpty()) {
      throw new ProductNotFoundException(
          String.format("Product with id %d does not exist", id));
    }
    return fetchedProduct.get();
  }
}
