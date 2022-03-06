package com.foods.angularwebapp.services;

import com.foods.angularwebapp.exceptions.InvalidRequestDataException;
import com.foods.angularwebapp.models.Order;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

import com.foods.angularwebapp.repositories.OrderRepository;
import com.foods.angularwebapp.utils.OrderStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private OrderRepository orderRepository;
  private EntityManager em;

  @Autowired
  public OrderService(OrderRepository repository, EntityManager em) {
    this.em = em;
    orderRepository = repository;
  }

  public List<Order> getUserOrders(String userId) {
    TypedQuery<Order> query = em.createQuery(
        "SELECT new com.foods.angularwebapp.models.Order(o.id, o.timePlaced, o.productQuantity, o.status, o.product) FROM Order o JOIN o.user u WHERE u.strId = ?1",
        Order.class);
    return query.setParameter(1, userId).getResultList();
  }

  public Order saveOrder(Order order) {
    if (!(order.getStatus() instanceof OrderStatus)) {
      throw new InvalidRequestDataException("Invalid order data provided");
    }

    // update existing order
    if (order.getId() != null) {
      try {
        Order o = orderRepository.findById(order.getId()).get();
        // Order o = orderRepository.getById(order.getId());
        OrderStatus currentStatus = o.getStatus();
        if (currentStatus != OrderStatus.DELIVERED) {
          if (currentStatus == OrderStatus.PENDING)
            o.setStatus(OrderStatus.PROCESSING);
          if (currentStatus == OrderStatus.PROCESSING)
            o.setStatus(OrderStatus.DELIVERED);
          orderRepository.save(o);
        }
        return o;
      } catch (EntityNotFoundException e) {
        throw new InvalidRequestDataException("Order does not exist");
      }
    }
    // save newly created order
    return orderRepository.save(order);
  }

  public List<Order> getAllOrders() {
    TypedQuery<Order> query = em.createQuery(
        "SELECT new com.foods.angularwebapp.models.Order(o.id, o.timePlaced, o.productQuantity, o.status, o.product) FROM Order o",
        Order.class);
    return query.getResultList();
  }
}
