package models;

import exceptions.UserNotFoundException;
import repos.DPRepo;
import repos.DiscountRepo;
import repos.OrderRepo;
import repos.UserRepo;
import services.*;

import static utils.GlobalConstants.scanner;
import static utils.Validate.*;

public class FoodOrderApp {

    private UserRepo userRepo;
    private DPRepo dpRepo;
    private MenuService menuService;
    private OrderService orderService;
    private OrderRepo orderRepo;
    private DiscountRepo discountRepo;

    public FoodOrderApp() {

        // Shared Repositories
        this.userRepo = new UserRepo();
        this.dpRepo = new DPRepo();
        this.orderRepo = new OrderRepo();
        this.discountRepo=new DiscountRepo();

        // Shared Core Services
        this.menuService = new MenuService();
        this.orderService = new OrderService(menuService);
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
                        System.out.println(e.getClass().getSimpleName());
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

        System.out.print(" Enter ID: ");
        int id = validateInt();

        User user = userRepo.getUserById(id);

        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }

        System.out.print(" Enter your password: ");
        String password = scanner.nextLine();

        if (!user.getPassword().equals(password)) {
            System.out.println(" Incorrect password!");
            return;
        }

        System.out.println("\n Login Successful!");
        System.out.println("------------------------------------------------");

        redirectUser(user);
    }

    private void redirectUser(User user) {

        switch (user.getUserType()) {

            case ADMIN:
                System.out.println("Welcome Admin, " + user.getName());
                AdminService adminService =
                        new AdminService(menuService, dpRepo, userRepo,discountRepo);
                adminService.displayFeatures();
                break;

            case CUSTOMER:
                System.out.println("Welcome Customer, " + user.getName());
                CustomerService customerService =
                        new CustomerService(menuService, orderService, dpRepo, user,orderRepo);
                customerService.displayFeatures();
                break;

            case DELIVERY_PARTNER:
                System.out.println("Welcome Delivery Partner, " + user.getName());
                DeliveryPartnerService deliveryPartnerService =
                        new DeliveryPartnerService(dpRepo, user);
                deliveryPartnerService.displayFeatures();
                break;
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

        User customer =
                new User(customerName, customerPassword, UserType.CUSTOMER);

        userRepo.addUser(customer);

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