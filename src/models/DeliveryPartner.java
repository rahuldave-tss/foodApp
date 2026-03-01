package models;

import notifications.Observer;

public class DeliveryPartner extends User implements Observer {
    private boolean isAvailable;

    public DeliveryPartner(String name, String password, String email, String phoneNumber) {
        super(name, password, email, phoneNumber,Role.DELIVERY_PARTNER);
        this.isAvailable=true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return String.format(
                "| %-2d | %-14s | %-14s | %-20s | %-12s | %-17s | %-10s |",
                getId(),
                getName(),
                getPassword(),
                getEmail(),
                getPhoneNumber(),
                getRole(),
                isAvailable
        );
    }

    @Override
    public void update(Order order) {
        System.out.println("Delivery Partner Notification: Order " + order.getOrderId() + " status changed to " + order.getStatus());
    }
}
