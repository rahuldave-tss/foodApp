package models;

import factory.UserFactory;

public class UserConfig {

    static {
        UserFactory.registerUser("ADMIN",
                (name, pass, email, phone) ->
                        new Admin(name, pass, email, phone));

        UserFactory.registerUser("CUSTOMER",
                (name, pass, email, phone) ->
                        new Customer(name, pass, email, phone));

        UserFactory.registerUser("DELIVERY_PARTNER",
                (name, pass, email, phone) ->
                        new DeliveryPartner(name, pass, email, phone));
    }
}