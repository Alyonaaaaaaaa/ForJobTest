package boyarina.trainy.streamApi;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0),
                new Order("Iphone", 1000.0)
        );
        System.out.println(orders
                .stream()
                .collect(Collectors.groupingBy((Order::getProduct), Collectors.summingDouble(Order::getCost)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList())
        );
    }

    @Data
    public static class Order {
        private String product;
        private double cost;

        public Order(String product, double cost) {
            this.product = product;
            this.cost = cost;
        }

        public String getProduct() {
            return product;
        }

        public double getCost() {
            return cost;
        }
    }
}
