package models;

import notifications.Observer;
import notifications.Subject;
import utils.RandomNumberGenerator;

import java.util.List;

public class Order implements Subject {
    private int orderId;
    private Cart cart;
    private double finalAmount;
    private User deliveryPartner;
    private OrderStatus status;
    private User customer;
    private List<Observer> observerList;

    public Order() {
        this.orderId = RandomNumberGenerator.generateRandomNumber();;
        this.cart = new Cart();
        this.status = OrderStatus.PREPARING;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public Cart getCart() {
        return cart;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public User getDeliveryPartner() {
        return deliveryPartner;
    }

    public void setDeliveryPartner(User deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Order ID       : ").append(orderId).append("\n");
        sb.append("Customer       : ").append(customer != null ? customer.getName() : "Not Assigned").append("\n");
        sb.append("Delivery Partner: ").append(deliveryPartner != null ? deliveryPartner.getName() : "Not Assigned").append("\n");
        sb.append("Status         : ").append(status).append("\n");
        sb.append("Final Amount   : ").append(finalAmount).append("\n");
        sb.append("Cart Items     : \n");

        if(cart != null && cart.getShoppingCart() != null && !cart.getShoppingCart().isEmpty()) {
            int index = 1;
            for(var item : cart.getShoppingCart().values()) {
                sb.append("  ")
                        .append(index++)
                        .append(". ")
                        .append(item.getFoodItem().getName())
                        .append(" x")
                        .append(item.getQuantity())
                        .append(" = ")
                        .append(item.getFoodItem().getPrice() * item.getQuantity())
                        .append("\n");
            }
        } else {
            sb.append("  Cart is empty\n");
        }

        return sb.toString();
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for(Observer observer : observerList) {
            observer.update(message);
        }
    }
}