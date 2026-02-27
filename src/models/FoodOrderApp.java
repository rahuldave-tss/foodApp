package models;

import repos.DPRepo;
import repos.UserRepo;
import services.*;

import static utils.GlobalConstants.scanner;
import static utils.Validate.*;

public class FoodOrderApp {

    private UserRepo userRepo;
    private DPRepo dpRepo;
    private MenuService menuService;
    private OrderService orderService;

    public FoodOrderApp() {

        // Shared repos
        this.userRepo = new UserRepo();
        this.dpRepo = new DPRepo();

        // Shared core services
        this.menuService = new MenuService();
        this.orderService = new OrderService(menuService);
    }

    public void start() throws InterruptedException {

        while (true) {

            System.out.println("=== Welcome to Food Order App ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = validateInt();

            switch (choice) {
                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("Thank you for using Food Order App. Goodbye!");
                    return;

                default:
                    System.out.println("Enter a valid choice !!");
            }
        }
    }

    private void login() throws InterruptedException {

        System.out.println("=== Login ===");
        System.out.print("Enter Id: ");
        int id = validateInt();

        User user = userRepo.getUserById(id);

        if (user == null) {
            System.out.println("Invalid ID !!");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (!user.getPassword().equals(password)) {
            System.out.println("Wrong Password !!");
            return;
        }

        System.out.println("Login Successful!");

        redirectUser(user);
    }

    private void redirectUser(User user) throws InterruptedException {

        switch (user.getUserType()) {

            case ADMIN:
                System.out.println("Welcome Admin, " + user.getName());
                AdminService adminService =
                        new AdminService(menuService, dpRepo, userRepo);
                adminService.displayFeatures();
                break;

            case CUSTOMER:
                System.out.println("Welcome Customer, " + user.getName());
                CustomerService customerService =
                        new CustomerService(menuService, orderService, dpRepo, user);
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

            System.out.println("=== Registration ===");
            System.out.println("1. Customer");
            System.out.println("2. Back");

            int choice = validateInt();

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Enter a valid choice !!");
            }
        }
    }

    private void registerCustomer() {

        String customerName = inputName();
        String customerPassword = inputPassword();

        User customer = new User(customerName, customerPassword, UserType.CUSTOMER);

        userRepo.addUser(customer);

        System.out.println("New Customer Registered with ID: " + customer.getId());
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