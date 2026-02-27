package factory;

import models.User;
import java.util.HashMap;
import java.util.Map;

public class UserFactory {

    private static final Map<String, UserCreator> registry = new HashMap<>();

    private UserFactory() {} // prevent instantiation

    public static void registerUser(String type, UserCreator creator) {
        registry.put(type.toUpperCase(), creator);
    }

    public static User createUser(String type,
                                  String name,
                                  String password,
                                  String email,
                                  String phoneNumber) {

        UserCreator creator = registry.get(type.toUpperCase());

        if (creator == null) {
            throw new IllegalArgumentException("Unknown user type: " + type);
        }

        return creator.create(name, password, email, phoneNumber);
    }
}