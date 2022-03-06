package com.foods.angularwebapp.repositories;

import java.util.List;
import com.foods.angularwebapp.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
  public List<Order> findAll();
}
