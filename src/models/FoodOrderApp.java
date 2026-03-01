package models;

import controllers.AdminController;
import controllers.CustomerController;
import controllers.DeliveryPartnerController;
import exceptions.UserNotFoundException;
import factory.UserFactory;
import repos.*;
import services.*;

import static utils.GlobalConstants.scanner;
import static utils.Validate.*;

public class FoodOrderApp {

    private UserRepo userRepo;
    private DPRepo dpRepo;
    private MenuRepo menuRepo;
    private CustomerRepo customerRepo;
    private DiscountRepo discountRepo;
    private DiscountService discountService;

    public FoodOrderApp() {
        this.userRepo = new UserRepo();
        this.dpRepo = new DPRepo();
        this.menuRepo = new MenuRepo();
        this.discountRepo=new DiscountRepo();
        this.customerRepo=new CustomerRepo();

        this.discountService=new DiscountService(discountRepo);
    }

    public void start() {

        while (true) {
            System.out.println("\n================================================");
            System.out.println("              WELCOME TO FOOD ORDER APP        ");
            System.out.println("================================================");
            System.out.println(" Please choose an option:");
            System.out.println("------------------------------------------------");
            System.out.println("  1. Register");
            System.out.println("  2. Login");
            System.out.println("  3. Exit");
            System.out.println("------------------------------------------------");
            System.out.print(" Enter your choice (1-3): ");

            int choice = validateInt();
            System.out.println();

            switch (choice) {
                case 1:
                    register();
                    break;

                case 2:
                    try{
                        login();
                    }
                    catch (UserNotFoundException e){
                        System.out.println("Exception: "+e.getClass().getSimpleName());
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println(" Thank you for using Food Order App. Goodbye!");
                    System.out.println("================================================\n");
                    return;

                default:
                    System.out.println(" Invalid choice! Please enter a number between 1 and 3.");
            }
        }
    }

    private void login() {

        System.out.println("\n================================================");
        System.out.println("                     LOGIN                      ");
        System.out.println("================================================");

        System.out.print("Enter ID: ");
        int id = validateInt();

        User user = userRepo.getUserById(id);

        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password!");
            return;
        }

        System.out.println("\nLogin Successful!");
        System.out.println("------------------------------------------------");

        redirectUser(user);

    }

    private void redirectUser(User user) {

        switch (user.getRole()){
            case ADMIN:{
                AdminController adminController=new AdminController(new AdminService(menuRepo,dpRepo,userRepo,discountRepo,customerRepo));
                System.out.println("Welcome Admin, "+user.getName()+"!");
                adminController.start();
                break;
            }
            case CUSTOMER:{
                CustomerController customerController=new CustomerController(new CustomerService(dpRepo,discountRepo,user,discountService),menuRepo,user);
                System.out.println("Welcome Customer, "+user.getName()+"!");
                customerController.start();
                break;
            }
            case DELIVERY_PARTNER:{
                DeliveryPartnerController deliveryPartnerController=new DeliveryPartnerController(new DeliveryPartnerService(dpRepo,user));
                System.out.println("Welcome Delivery Partner, "+user.getName()+"!");
                deliveryPartnerController.start();
                break;
            }
        }
    }


    private void register() {

        while (true) {

            System.out.println("\n================================================");
            System.out.println("                   REGISTRATION                 ");
            System.out.println("================================================");
            System.out.println(" Please choose an option:");
            System.out.println("------------------------------------------------");
            System.out.println("  1. Customer");
            System.out.println("  2. Back");
            System.out.println("------------------------------------------------");
            System.out.print(" Enter your choice (1-2): ");

            int choice = validateInt();
            System.out.println();

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;

                case 2:
                    return;

                default:
                    System.out.println(" Invalid choice! Please enter 1 or 2.");
            }
        }
    }

    private void registerCustomer() {

        System.out.println("\n------------------------------------------------");
        System.out.println("              CUSTOMER REGISTRATION             ");
        System.out.println("------------------------------------------------");

        String customerName = inputName();
        String customerPassword = inputPassword();
        String customerEmail=validateEmail();
        String customerPhoneNumber=validatePhoneNumber();

        User customer =
                UserFactory.createUser(Role.CUSTOMER,customerName,customerPassword,customerEmail,customerPhoneNumber);

        userRepo.addUser(customer);
        customerRepo.addCustomer(customer);

        System.out.println("\n New Customer Registered Successfully!");
        System.out.println(" Your Customer ID is: " + customer.getId());
        System.out.println("------------------------------------------------\n");
    }


    private String inputPassword() {
        System.out.print("Enter your password: ");
        return scanner.nextLine();
    }

    private String inputName() {
        System.out.print("Enter your name: ");
        return validateString();
    }
}