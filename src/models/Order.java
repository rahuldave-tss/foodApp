package models;

public class Order {

    private static int counter = 101;

    private int orderId;
    private Cart cart;
    private double finalAmount;
    private User deliveryPartner;
    private OrderStatus status;

    public Order() {
        this.orderId = counter++;
        this.cart = new Cart();
        this.status = OrderStatus.PREPARING;
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
        return "Order{" +
                "orderId=" + orderId +
                ", cart=" + cart +
                ", finalAmount=" + finalAmount +
                '}';
    }
}