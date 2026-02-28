package controllers;

import exceptions.*;
import factory.UserFactory;
import models.*;
import services.AdminService;
import utils.Validate;

import java.util.List;

import static utils.GlobalConstants.*;
import static utils.Validate.*;

public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public void start() {

        while (true) {
            System.out.println("\n==================================================");
            System.out.println("                  ADMIN PANEL                     ");
            System.out.println("==================================================");
            System.out.println("1. Manage Menu");
            System.out.println("2. Manage Discounts");
            System.out.println("3. Manage Delivery Partners");
            System.out.println("4. View All Customers");
            System.out.println("5. View All Delivery Partners");
            System.out.println("6. Logout");
            System.out.println("==================================================");
            System.out.print("Enter choice: ");

            int choice = validateInt();

            switch (choice) {
                case 1 -> manageMenu();
                case 2 -> manageDiscounts();
                case 3 -> manageDeliveryPartners();
                case 4 -> viewAllCustomers();
                case 5 -> viewAllDeliveryPartners();
                case 6 -> {
                    System.out.println("\nLogging out...");
                    return;
                }
                default -> System.out.println("Invalid choice !!");
            }
        }
    }

    private void viewAllDeliveryPartners() {
        System.out.println("\n-------------- DELIVERY PARTNERS ----------------");
        List<DeliveryPartner> deliveryPartnerList = adminService.getAllDeliveryPartners();
        deliveryPartnerList.forEach(System.out::println);
        System.out.println("-------------------------------------------------\n");
    }

    private void viewAllCustomers() {
        System.out.println("\n------------------- CUSTOMERS -------------------");
        List<Customer> customerList = adminService.getAllCustomers();
        customerList.forEach(System.out::println);
        System.out.println("-------------------------------------------------\n");
    }

    private void manageMenu() {

        System.out.println("\n================= MANAGE MENU =================");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. View Items");
        System.out.println("===============================================");
        System.out.print("Enter choice: ");

        int choice = validateInt();

        switch (choice) {
            case 1: {
                viewAllItems();
                System.out.println("\n----------- ADD NEW ITEM -----------");
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter price: ");
                double price = validateDouble();

                try {
                    int id = adminService.addItem(name, price);
                    System.out.println("\nItem added successfully with ID: " + id);
                } catch (ItemAlreadyPresentException e) {
                    System.out.println("\n" + e.getClass().getSimpleName());
                    System.out.println(e.getMessage());
                }
                break;
            }

            case 2: {
                viewAllItems();
                System.out.print("\nEnter item ID to remove: ");
                int id = validateInt();
                try {
                    boolean removed = adminService.removeItem(id);
                    System.out.println(removed ? "\nItem removed successfully." : "\nItem not found.");
                } catch (EmptyMenuException e) {
                    System.out.println("\nException: " + e.getClass().getSimpleName());
                    System.out.println(e.getMessage());
                }
                break;
            }

            case 3: {
                viewAllItems();
                break;
            }

            default:
                System.out.println("Invalid choice !!");
        }
    }

    private void viewAllItems() {
        System.out.println("\n------------------- MENU ITEMS -------------------");
        List<FoodItem> items = adminService.getAllItems();
        items.forEach(i ->
                System.out.printf("ID: %-5d Name: %-20s Price: %.2f%n",
                        i.getId(), i.getName(), i.getPrice()));
        System.out.println("--------------------------------------------------\n");
    }

    private void manageDiscounts() {

        System.out.println("\n=============== MANAGE DISCOUNTS ===============");
        System.out.println("1. View Discounts");
        System.out.println("2. Add Discount");
        System.out.println("3. Remove Discount");
        System.out.println("4. Update Discount");
        System.out.println("================================================");
        System.out.print("Enter choice: ");

        int choice = validateInt();

        switch (choice) {
            case 1: {
                viewAllDiscounts();
                break;
            }

            case 2: {
                System.out.println("\n----------- ADD DISCOUNT -----------");
                System.out.print("Enter amount: ");
                double amount = validateDouble();

                System.out.print("Enter percentage: ");
                double percentage = validateDouble();

                DiscountStrategy discount =
                        new AmountDiscount("Amount Discount", amount, percentage);
                adminService.addDiscount(discount);

                System.out.println("\nDiscount added successfully !!");
                break;
            }

            case 3: {
                viewAllDiscounts();
                System.out.print("Enter Discount ID to remove: ");
                int discountId = validateInt();

                if (!adminService.removeDiscount(discountId)) {
                    System.out.println("\nDiscount not available !!");
                    break;
                }
                System.out.println("\nDiscount removed successfully !!");
                break;
            }

            case 4: {
                viewAllDiscounts();
                System.out.print("Enter Discount ID to update: ");
                int discountId = validateInt();

                System.out.print("Enter new discount percentage: ");
                double newPercentage = validateDouble();

                adminService.updateDiscount(discountId, newPercentage);
                System.out.println("\nDiscount updated successfully !!");
                break;
            }

            default:
                System.out.println("Enter a valid choice !!");
        }
    }

    private void viewAllDiscounts() {
        System.out.println("\n---------------- DISCOUNT LIST ----------------");
        adminService.getAllDiscounts()
                .forEach(d ->
                        System.out.printf("ID: %-5d Amount: %-10.2f Percentage: %.2f%%%n",
                                d.getDiscountId(),
                                d.getAmount(),
                                d.getDiscountPercentage()));
        System.out.println("------------------------------------------------\n");
    }

    private void manageDeliveryPartners() {

        System.out.println("\n========== MANAGE DELIVERY PARTNERS ==========");
        System.out.println("1. Add Partner");
        System.out.println("2. Remove Partner");
        System.out.println("3. View Partners");
        System.out.println("==============================================");
        System.out.print("Enter choice: ");

        int choice = validateInt();

        switch (choice) {
            case 1: {
                System.out.println("\n----------- ADD DELIVERY PARTNER -----------");

                System.out.print("Enter name: ");
                String name = validateString();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                String email = validateEmail();
                String phoneNumber = validatePhoneNumber();

                User partner = UserFactory.createUser(
                        Role.DELIVERY_PARTNER,
                        name,
                        password,
                        email,
                        phoneNumber
                );

                adminService.addDeliveryPartner(partner);
                System.out.println("\nPartner added successfully with ID: " + partner.getId());
                break;
            }

            case 2: {
                viewAllDeliveryPartners();
                System.out.print("Enter Delivery Partner ID to remove: ");
                int partnerId = validateInt();
                adminService.removeDeliveryPartner(partnerId);
                break;
            }

            case 3: {
                viewAllDeliveryPartners();
                break;
            }
        }
    }
}