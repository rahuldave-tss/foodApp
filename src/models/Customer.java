package models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private List<Address> customerAddresses;

    public Customer(String name, String password, String email, String phoneNumber) {
        super(name, password, email, phoneNumber);
        this.customerAddresses=new ArrayList<>();
    }

    public void addAddress(Address address){
        customerAddresses.add(address);
    }
    public void removeAddress(Address address){
        customerAddresses.remove(address);
    }

}
