package com.foods.angularwebapp.repositories;

import com.foods.angularwebapp.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
