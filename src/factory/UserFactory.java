package factory;

import models.*;

import java.util.HashMap;
import java.util.Map;

public class UserFactory {

    private UserFactory() {} // prevent instantiation

    public static User createUser(Role role,
                                  String name,
                                  String password,
                                  String email,
                                  String phoneNumber) {

        switch (role) {
            case ADMIN:
                return new Admin(name, password, email, phoneNumber);

            case CUSTOMER:
                return new Customer(name, password, email, phoneNumber);

            case DELIVERY_PARTNER:
                return new DeliveryPartner(name, password, email, phoneNumber);

            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
}