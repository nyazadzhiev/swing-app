package generators;
import models.Order;
import models.Status;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {
    private static final Random random = new Random();
    private static final String[] orderIdPrefixes = {"A", "B", "C", "D", "E"};


    // Generate a random list of orders
    public static List<Order> generateOrders(int numberOfOrders) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < numberOfOrders; i++) {
            Order order = new Order();
            order.setClient(generateRandomUser());
            order.setCourier(generateRandomUser());
            order.setStatus(generateRandomStatus());
            orders.add(order);
        }
        return orders;
    }

    // Generate a random order ID
    private static String generateOrderId() {
        String prefix = orderIdPrefixes[random.nextInt(orderIdPrefixes.length)];
        int suffix = random.nextInt(1000);
        return prefix + "-" + suffix;
    }

    // Generate a random user
    private static User generateRandomUser() {
        // Implementation of generating a random user is needed
        return new User(); // Dummy return
    }

    // Generate a random status
    private static Status generateRandomStatus() {
        Status[] statuses = Status.values();
        return statuses[random.nextInt(statuses.length)];
    }
}
