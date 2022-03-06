package com.foods.angularwebapp.repositories;

import com.foods.angularwebapp.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  public User findByStrId(String id);
}
