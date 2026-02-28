package models;

public class Admin extends User{

    public Admin(String name, String password, String email, String phoneNumber) {
        super(name, password, email, phoneNumber,Role.ADMIN);
    }

}
