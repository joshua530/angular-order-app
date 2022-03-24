package com.foods.angularwebapp.config;

import com.foods.angularwebapp.repositories.OrderRepository;
import com.foods.angularwebapp.repositories.ProductRepository;
import com.foods.angularwebapp.repositories.UserRepository;
import com.foods.angularwebapp.security.Encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.foods.angularwebapp.models.*;
import com.foods.angularwebapp.utils.OrderStatus;

@Configuration
public class DbConfig {
        @Value("${application.secretkey}")
        private String salt;

        @Autowired
        private Encryption encryption;

        /**
         * generates a random double for a product's price
         */
        private Double generatePrice() {
                double random = ThreadLocalRandom.current().nextDouble(12.99, 40.58);
                int tmp = (int) (random * 100);
                double price = ((double) tmp) / 100;
                return price;
        }

        /**
         * generates a random time string
         */
        private String generateTime() {
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM YYYY h:mm a");
                Date date = new Date();
                // generate a random date between January 20, 2022 and now
                String time = formatter.format(
                                new Date(
                                                (int) ((Math.random() * (date.getTime() - 1642661718)) + 1642661718)));
                return time;
        }

        @Bean
        CommandLineRunner cmd(ProductRepository pr, UserRepository ur,
                        OrderRepository or) {

                return args -> {
                        Product p = new Product();
                        p.setId(1);
                        Product p2 = new Product();
                        p2.setId(2);
                        User u = new User();
                        u.setId(1);
                        User u2 = new User();
                        u2.setId(2);

                        User john = new User("john", true);
                        john.setAdmin(true);
                        john.setStrId(UUID.randomUUID().toString());
                        String encryptedPass = encryption.encryptPassword("password", salt);
                        john.setPassword(encryptedPass);
                        john.setUsername("john");

                        User alice = new User();
                        alice.setUsername("alice");
                        alice.setStrId(UUID.randomUUID().toString());

                        User jane = new User();
                        jane.setUsername("jane");
                        alice.setStrId(UUID.randomUUID().toString());

                        ur.save(john);
                        ur.save(alice);
                        ur.save(jane);

                        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec id nulla sit amet nulla dictum placerat. Vivamus lacinia ornare vehicula. Ut consectetur massa justo. Sed non fermentum eros, sit amet pulvinar lacus. Donec facilisis aliquam condimentum. Ut et venenatis sapien, vel ornare neque. Suspendisse sit amet egestas erat, quis tempus nisl.";
                        String nutritionInfo = "In eget porta nulla. Sed dignissim neque leo, nec accumsan quam accumsan vel. Sed condimentum congue eros. Suspendisse sagittis risus ligula, nec semper ante imperdiet suscipit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras blandit, massa id vulputate fermentum, erat tellus rhoncus justo, et facilisis tellus eros id nisl. Suspendisse fringilla sem ut tortor sagittis viverra. Etiam convallis vitae felis nec congue.";

                        String staticUrl = "http://localhost/static";

                        // featured
                        Product pizza = new Product(
                                        "pizza",
                                        generatePrice(), description,
                                        staticUrl + "/images/pizza.png", nutritionInfo, true, false);
                        Product chicken = new Product(
                                        "chicken",
                                        generatePrice(), description,
                                        staticUrl + "/images/chicken.png",
                                        nutritionInfo, true, false);
                        Product yogurt = new Product(
                                        "yogurt",
                                        generatePrice(), description,
                                        staticUrl + "/images/yogurt.png",
                                        nutritionInfo, true, false);
                        Product milkShake = new Product(
                                        "milk shake",
                                        generatePrice(), description,
                                        staticUrl + "/images/milk_shake.png",
                                        nutritionInfo, true, false);

                        // hot
                        Product roastedRibs = new Product(
                                        "roasted ribs",
                                        generatePrice(), description,
                                        staticUrl + "/images/roasted_ribs.png", nutritionInfo, false, true);
                        Product hotDog = new Product(
                                        "hot dog",
                                        generatePrice(), description,
                                        staticUrl + "/images/hot_dog.png", nutritionInfo, false, true);
                        Product fish = new Product(
                                        "fish",
                                        generatePrice(), description,
                                        staticUrl + "/images/fish.png", nutritionInfo, false, true);
                        Product friedRice = new Product(
                                        "fried rice",
                                        generatePrice(), description,
                                        staticUrl + "/images/fried_rice.png", nutritionInfo, false, true);

                        // others
                        Product rice = new Product(
                                        "rice",
                                        generatePrice(), description,
                                        staticUrl + "/images/rice.png", nutritionInfo, false, false);
                        Product coffee = new Product(
                                        "coffee",
                                        generatePrice(), description,
                                        staticUrl + "/images/coffee_latte.png", nutritionInfo, false, false);
                        Product sandwich = new Product(
                                        "sandwich",
                                        generatePrice(), description,
                                        staticUrl + "/images/sandwich.png", nutritionInfo, false, false);
                        Product fruitSalad = new Product(
                                        "fruit salad",
                                        generatePrice(), description,
                                        staticUrl + "/images/fruit_salad.png", nutritionInfo, false, false);
                        Product omelette = new Product(
                                        "omelette",
                                        generatePrice(), description,
                                        staticUrl + "/images/omelette.png", nutritionInfo, false, false);
                        Product cookies = new Product(
                                        "cookies",
                                        generatePrice(), description,
                                        staticUrl + "/images/cookies.png", nutritionInfo, false, false);
                        Product burger = new Product(
                                        "burger",
                                        generatePrice(), description,
                                        staticUrl + "/images/burger.png", nutritionInfo, false, false);
                        Product pasta = new Product(
                                        "pasta",
                                        generatePrice(), description,
                                        staticUrl + "/images/pasta.png", nutritionInfo, false, false);

                        pr.save(pizza);
                        pr.save(chicken);
                        pr.save(yogurt);
                        pr.save(milkShake);
                        pr.save(roastedRibs);
                        pr.save(hotDog);
                        pr.save(fish);
                        pr.save(friedRice);
                        pr.save(rice);
                        pr.save(coffee);
                        pr.save(sandwich);
                        pr.save(fruitSalad);
                        pr.save(omelette);
                        pr.save(cookies);
                        pr.save(burger);
                        pr.save(pasta);

                        Order order1 = new Order(generateTime(), 3, OrderStatus.DELIVERED, jane, rice);
                        Order order2 = new Order(generateTime(), 23, OrderStatus.PENDING, alice, roastedRibs);
                        Order order3 = new Order(generateTime(), 3, OrderStatus.PENDING, jane, sandwich);
                        Order order4 = new Order(generateTime(), 23, OrderStatus.PENDING, alice,
                                        cookies);
                        Order order5 = new Order(generateTime(), 3, OrderStatus.PROCESSING, jane, milkShake);
                        Order order6 = new Order(generateTime(), 3, OrderStatus.DELIVERED, jane, sandwich);
                        Order order7 = new Order(generateTime(), 23, OrderStatus.PENDING, alice, coffee);
                        Order order8 = new Order(generateTime(), 23, OrderStatus.PENDING, alice, chicken);

                        or.save(order1);
                        or.save(order2);
                        or.save(order3);
                        or.save(order4);
                        or.save(order5);
                        or.save(order6);
                        or.save(order7);
                        or.save(order8);
                };
        }
}
