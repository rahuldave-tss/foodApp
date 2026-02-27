package notifications;

import models.User;

public class DeliveryPartnerNotification implements Observer{
    private User deliveryPartner;

    public DeliveryPartnerNotification(User deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }


    @Override
    public void update(String message) {
        System.out.println("Notification for Delivery Partner " + deliveryPartner.getName() + ": " + message);
    }
}
