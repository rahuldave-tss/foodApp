package models;

public class DeliveryPartner extends User{
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
                "+----+----------------+----------------+----------------------+--------------+-------------------+------------+\n" +
                        "| ID | Name           | Password       | Email                | Phone Number | Role              | Available  |\n" +
                        "+----+----------------+----------------+----------------------+--------------+-------------------+------------+\n" +
                        "| %-2d | %-14s | %-14s | %-20s | %-12s | %-17s | %-10s |\n" +
                        "+----+----------------+----------------+----------------------+--------------+-------------------+------------+",
                getId(),
                getName(),
                getPassword(),
                getEmail(),
                getPhoneNumber(),
                getRole(),
                isAvailable
        );
    }
}
