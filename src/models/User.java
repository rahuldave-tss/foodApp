package models;

import utils.RandomNumberGenerator;

public abstract class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;

    public User(String name, String password,String email,String phoneNumber) {
        this.id = RandomNumberGenerator.generateRandomNumber();
        this.name = name;
        this.password = password;
        this.email=email;
        this.phoneNumber=phoneNumber;
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

}
