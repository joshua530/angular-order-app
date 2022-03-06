package com.foods.angularwebapp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.foods.angularwebapp.exceptions.AuthenticationException;
import com.foods.angularwebapp.exceptions.InvalidRequestDataException;
import com.foods.angularwebapp.models.Order;
import com.foods.angularwebapp.models.Product;
import com.foods.angularwebapp.models.User;
import com.foods.angularwebapp.repositories.ProductRepository;
import com.foods.angularwebapp.repositories.UserRepository;
import com.foods.angularwebapp.security.Auth;
import com.foods.angularwebapp.services.OrderService;
import com.foods.angularwebapp.utils.AuthToken;
import com.foods.angularwebapp.utils.OrderStatus;
import com.foods.angularwebapp.utils.OrderUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class OrderController {
  private OrderService orderService;
  private UserRepository userRepository;
  private ProductRepository productRepository;
  private Auth authentication;

  @Autowired
  public OrderController(OrderService orderService, UserRepository userRepository, ProductRepository productRepository,
      Auth authentication) {
    this.orderService = orderService;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.authentication = authentication;
  }

  @GetMapping("{user-id}")
  public List<Order> getUserOrders(@PathVariable("user-id") String userId) {
    return this.orderService.getUserOrders(userId);
  }

  @PostMapping
  public Order createOrder(@RequestBody Order newOrder) {
    Integer productId = newOrder.getProductIdNum();
    String userId = newOrder.getUserIdStr();
    Integer productQuantity = newOrder.getProductQuantity();
    OrderStatus orderStatus = newOrder.getStatus();

    Product productBeingOrdered;
    // validate order's product
    if (productId == null || productId < 1) {
      throw new RuntimeException("Product id cannot be empty");
    } else {
      Optional<Product> fetched = productRepository.findById(productId);

      if (fetched.isEmpty()) {
        throw new RuntimeException("Invalid product id");
      }
      productBeingOrdered = fetched.get();
    }

    // validate order's user
    User user;
    if (userId == null) {
      throw new RuntimeException("Invalid user id");
    } else {
      userId = userId.trim();
      if (userId.isBlank() || userId.length() < 10)
        throw new RuntimeException("Invalid user id");

      user = userRepository.findByStrId(userId);
      // new user, create user details
      if (user == null) {
        user = new User();
        user.setAdmin(false);
        user.setUsername(userId);
        user.setStrId(userId);
        userRepository.save(user);
      }
    }
    // check time placed
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMM YYYY h:mm a");
    String time = formatter.format(new Date());

    // check product quantity
    if (productQuantity == null || productQuantity < 1) {
      throw new RuntimeException("Invalid product quantity");
    }

    // set status to that for first order
    orderStatus = OrderStatus.PENDING;

    Order toSave = new Order();
    toSave.setProduct(productBeingOrdered);
    toSave.setUser(user);
    toSave.setTimePlaced(time);
    toSave.setProductQuantity(productQuantity);
    toSave.setStatus(orderStatus);

    Order savedOrder = this.orderService.saveOrder(toSave);
    savedOrder.setUser(null);

    return savedOrder;
  }

  @PostMapping(path = "all")
  public List<Order> getAllOrders(@RequestBody AuthToken token) {
    if (!authentication.userIsAuthenticated(token.getToken())) {
      throw new AuthenticationException("You don't have permission to access this route");
    }
    return this.orderService.getAllOrders();
  }

  @PostMapping(path = "update")
  public Order processOrder(@RequestBody OrderUpdateRequest requestBody) {
    Order order = new Order();
    order.setId(requestBody.getOrderId());
    order.setStatus(requestBody.getStatus());
    String token = requestBody.getToken();

    if (!authentication.userIsAuthenticated(token)) {
      throw new AuthenticationException("You don't have permission to access this route");
    }

    Integer id = order.getId();
    if (id == null) {
      throw new InvalidRequestDataException("Invalid order data provided");
    }

    Order saved = orderService.saveOrder(order);
    saved.setUser(null); // hide sensitive user details
    return saved;
  }
}
