package notifications;

import models.User;

public class CustomerNotification implements Observer{
    private User customer;

    public CustomerNotification(User customer) {
        this.customer = customer;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification for " + customer.getName() + ": " + message);
    }
}
