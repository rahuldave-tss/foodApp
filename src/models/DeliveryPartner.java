package models;

public class DeliveryPartner extends User{
    private boolean isAvailable;

    public DeliveryPartner(String name, String password, String email, String phoneNumber) {
        super(name, password, email, phoneNumber);
        this.isAvailable=true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
