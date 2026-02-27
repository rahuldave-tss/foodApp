package repos;

import models.Customer;
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

    public List<Order> getOrdersByCustomer(Customer customer) {
        return orderHistory.stream()
                .filter(order -> order.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }
}
