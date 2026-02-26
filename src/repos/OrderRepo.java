package repos;

import models.Order;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepo {
    List<Order> orderHistory;

    public OrderRepo() {
        orderHistory=new ArrayList<>();
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getAllOrders() {
        return orderHistory;
    }

    public List<Order> getOrdersByCustomer(User user) {
        return orderHistory.stream()
                .filter(order -> order.getCustomer().equals(user))
                .collect(Collectors.toList());
    }
}
