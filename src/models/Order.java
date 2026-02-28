package models;

import notifications.Observer;
import notifications.Subject;
import utils.RandomNumberGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements Subject {
    private int orderId;
    private double finalAmount;
    private DeliveryPartner deliveryPartner;
    private OrderStatus status;
    private Customer customer;
    private Map<FoodItem,OrderItem> items;
    private List<Observer> observerList;

    public Order(Map<FoodItem,OrderItem> items,double finalAmount,Customer customer) {
        this.orderId = RandomNumberGenerator.generateRandomNumber();
        this.status = OrderStatus.CREATED;
        this.items=items;
        this.finalAmount=finalAmount;
        this.customer=customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
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

    public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return String.format(
                "==============================\n" +
                        "            ORDER DETAILS\n" +
                        "==============================\n" +
                        "Order ID        : %d\n" +
                        "Final Amount    : %.2f\n" +
                        "Delivery Partner: %s\n" +
                        "Status          : %s\n" +
                        "Customer        : %s\n" +
                        "Items           : %s\n" +
                        "==============================",
                orderId,
                finalAmount,
                deliveryPartner,
                status,
                customer,
                items
        );
    }
}