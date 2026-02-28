package repos;

import models.Customer;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    private List<Customer> customerList;

    public CustomerRepo() {
        this.customerList = new ArrayList<>();
    }

    public void addCustomer(User customer){
        customerList.add((Customer) customer);
    }

    public void removeCustomer(Customer customer){
        customerList.remove(customer);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
