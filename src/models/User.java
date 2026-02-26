package models;

import utils.RandomNumberGenerator;

public class User {
    private int id;
    private String name;
    private String password;
    private UserType userType;

    public User(String name, String password, UserType userType) {
        this.id = RandomNumberGenerator.generateRandomNumber();
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }
}
