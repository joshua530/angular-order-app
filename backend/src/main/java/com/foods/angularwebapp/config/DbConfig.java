// package com.foods.angularwebapp.config;

// import com.foods.angularwebapp.repositories.OrderRepository;
// import com.foods.angularwebapp.repositories.ProductRepository;
// import com.foods.angularwebapp.repositories.UserRepository;
// import com.foods.angularwebapp.security.Encryption;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.UUID;

// import com.foods.angularwebapp.models.*;
// import com.foods.angularwebapp.utils.OrderStatus;

// @Configuration
// public class DbConfig {
// @Value("${application.secretkey}")
// private String salt;

// @Autowired
// private Encryption encryption;

// @Bean
// CommandLineRunner cmd(ProductRepository pr, UserRepository ur,
// OrderRepository or) {

// return args -> {
// Product p = new Product();
// p.setId(1);
// Product p2 = new Product();
// p2.setId(2);
// User u = new User();
// u.setId(1);
// User u2 = new User();
// u2.setId(2);

// User john = new User("john", true);
// john.setAdmin(true);
// john.setStrId(UUID.randomUUID().toString());
// String encryptedPass = encryption.encryptPassword("password", salt);
// john.setPassword(encryptedPass);
// john.setUsername("john");

// User alice = new User();
// alice.setUsername("alice");
// alice.setStrId(UUID.randomUUID().toString());

// User jane = new User();
// jane.setUsername("jane");
// alice.setStrId(UUID.randomUUID().toString());

// ur.save(john);
// ur.save(alice);
// ur.save(jane);

// Product food = new Product(
// "Food",
// 12.23, "Delicious food",
// "/images/food.jpg",
// "Contains vitamins A,C and D");

// Product pancakes = new Product(
// "Pancakes",
// 20.11, "Pancakes covered with honey",
// "/images/food6.jpg",

// "Rich in vitamins. Perfect way to start your day"

// );
// Product mixedSalad = new Product(
// "Mixed salad", 32.16,
// "Salad composed of different fruits and vegetables",
// "/images/food5.jpg",

// "All manner of vitamins, ranging from A to E are to be found here"

// );
// Product sandwich = new Product(
// "Bacon sandwich", 18.45, "Delicious bacon sandwich",
// "/images/food4.jpg",

// "Crunchy with a touch of healthy vegetables. A good snack to grab when" +
// " in need of a meal"

// );
// Product sausages = new Product(
// "Sausages", 22.89, "Golden brown barbecued sausages",
// "/images/food7.jpg",

// "A rich source of proteins. Ideal for people working towards gaining body" +
// "mass"

// );
// Product rice = new Product(
// "Beef steak with rice and broccoli", 44.38, "Scrumptious meal. The picture" +
// "tells it all",
// "/images/food2.jpg",

// "Contains all the three major nutrients. A heavy meal that will come in" +
// "handy" +
// " anytime you feel hungry"

// );
// Product eggs = new Product(
// "Eggs and salad", 23.33,
// "Boiled eggs accompanied with a fruit and vegetable mixture, sprinkled with"
// +
// "black pepper",
// "/images/food3.jpg",

// "Contains proteins, vitamins and essential minerals. Don\"t forget the black"
// +
// "pepper."

// );

// pr.save(rice);
// pr.save(food);
// pr.save(pancakes);
// pr.save(eggs);
// pr.save(sausages);
// pr.save(sandwich);
// pr.save(mixedSalad);

// Order order1 = new Order("Time", 3, OrderStatus.DELIVERED, jane, rice);
// Order order2 = new Order("Time 2", 23, OrderStatus.PENDING, alice, pancakes);
// Order order3 = new Order("Time", 3, OrderStatus.PENDING, jane, sandwich);
// Order order4 = new Order("Time 2", 23, OrderStatus.PENDING, alice,
// mixedSalad);
// Order order5 = new Order("Time", 3, OrderStatus.PROCESSING, jane, eggs);
// Order order6 = new Order("Time", 3, OrderStatus.DELIVERED, jane, sandwich);
// Order order7 = new Order("Time 2", 23, OrderStatus.PENDING, alice, food);
// Order order8 = new Order("Time 2", 23, OrderStatus.PENDING, alice, eggs);

// or.save(order1);
// or.save(order2);
// or.save(order3);
// or.save(order4);
// or.save(order5);
// or.save(order6);
// or.save(order7);
// or.save(order8);
// };
// }
// }
