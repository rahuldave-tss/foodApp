package models;

public class User {

    //builder
    private static int newId=1;
    private String name;
    private int id;
    private String password;
    private UserType userType;

    public User(){}
    public User(String name, String password, UserType userType) {
        this.name = name;
        this.id = newId++;
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
