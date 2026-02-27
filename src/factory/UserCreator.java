package factory;

import models.User;

@FunctionalInterface
public interface UserCreator {
    User create(String name, String password, String email, String phoneNumber);
}