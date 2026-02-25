package models;

import repos.DiscountRepo;
import repos.UserRepo;
import services.AdminService;
import services.CustomerService;
import services.DeliveryPartnerService;
import services.MenuService;

import java.util.Map;

import static utils.GlobalConstants.scanner;
import static utils.Validate.*;

public class FoodOrderApp {
    private UserRepo userRepo;
    private AdminService adminService;
    private CustomerService customerService;
    private DeliveryPartnerService deliveryPartnerService;


    public FoodOrderApp() {
        this.userRepo = new UserRepo();
        this.adminService=new AdminService();
        this.customerService=new CustomerService();
        this.deliveryPartnerService=new DeliveryPartnerService();
    }

    public void start() throws InterruptedException {
        while(true){
            System.out.println("===Welcome to Food Order App===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Enter your choice: ");
            int choice=validateInt();

            switch (choice){
                case 1:{
                    register();
                    break;
                }
                case 2:{
                    login();
                    break;
                }
                default:{
                    System.out.println("Enter a valid choice !!");
                }
            }
        }
    }

    private void login() throws InterruptedException {
        while (true){
            System.out.println("===Login===");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Delivery Partner");
            System.out.println("4. Back");
            int choice=validateInt();

            switch (choice){
                case 1:{
                    loginAdmin();
                    break;
                }
                case 2:{
                    loginCustomer();
                    break;
                }
                case 3:{
                    loginDeliveryPartner();
                    break;
                }
                case 4:{
                    System.out.println("Back to Main Menu...");
                    return;
                }
                default:{
                    System.out.println("Enter a valid choice !!");
                }
            }
        }

    }

    private void loginDeliveryPartner() throws InterruptedException {
        User user=getLoginDetails();
        if(user==null)return;
        System.out.println("Welcome Delivery Partner ,"+user.getName());
        deliveryPartnerService.displayFeatures();
    }

    private void loginCustomer() throws InterruptedException {
        User user=getLoginDetails();
        if(user==null)return;
        System.out.println("Welcome Customer ,"+user.getName());
        customerService.displayFeatures();
    }

    private void loginAdmin() {
        User user=getLoginDetails();
        if(user==null)return;
        System.out.println("Welcome Admin ,"+user.getName());
        adminService.displayFeatures();
    }

    private User getLoginDetails() {
        System.out.print("Enter Id: ");
        int id=validateInt();
        User user=userRepo.getUserById(id);
        if(user==null){
            System.out.println("Invalid ID !!");
            return null;
        }
        String password=inputPassword();
        if(!user.getPassword().equals(password)){
            System.out.println("Wrong Password !!");
            return null;
        }
        return user;
    }

    private void register() {
        while(true){
            System.out.println("===Registration===");
            System.out.println("1. Customer");
            System.out.println("2. Back");
            int choice=validateInt();

            switch (choice){
                case 1:{
                    registerCustomer();
                    break;
                }
                case 2:{
                    System.out.println("Back to Main Menu...");
                    return;
                }
                default:{
                    System.out.println("Enter a valid choice !!");
                }
            }
        }

    }

//    private void registerDeliveryPartner() {
//        String partnerName=inputName();
//        String partnerPassword=inputPassword();
//
//        userRepo.addUser(new User(partnerName,partnerPassword,UserType.DELIVERY_PARTNER));
//        System.out.println("New Delivery Partner Registered !!");
//    }

    private void registerCustomer() {
        String customerName=inputName();
        String customerPassword=inputPassword();

        User customer=new User(customerName,customerPassword,UserType.CUSTOMER);
        userRepo.addUser(customer);
        System.out.println("New Customer Registered with ID: "+customer.getId());
    }

    private String inputPassword() {
        System.out.print("Enter your password: ");
        String password= scanner.nextLine();
        return password;
    }

    private String inputName() {
        System.out.print("Enter your name: ");
        String name=validateString();
        return name;
    }
}
