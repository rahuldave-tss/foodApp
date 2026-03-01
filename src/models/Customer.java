package models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private String customerAddress;
    private Cart cart;
    private List<Order> orderHistory;

    public Customer(String name, String password, String email, String phoneNumber) {
        super(name, password, email, phoneNumber,Role.CUSTOMER);
        this.cart=new Cart();
        this.orderHistory=new ArrayList<>();
    }

    public Cart getCart() {
        return cart;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addOrderToHistory(Order order){
        orderHistory.add(order);
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    @Override
    public String toString() {
        return String.format(
                "| %-2d | %-14s | %-14s | %-20s | %-12s | %-8s | %-20s |",
                getId(),
                getName(),
                getPassword(),
                getEmail(),
                getPhoneNumber(),
                getRole(),
                customerAddress
        );
    }


}
