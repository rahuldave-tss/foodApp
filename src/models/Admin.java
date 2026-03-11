package models;

public class Admin extends User{

    public Admin(String userName,String name, String password, String email, String phoneNumber) {
        super(userName,name, password, email, phoneNumber,Role.ADMIN);
    }

}
