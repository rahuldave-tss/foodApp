package controllers;

import models.*;
import services.AdminService;
import utils.Validate;

import java.util.List;
import java.util.Scanner;

import static utils.GlobalConstants.scanner;

public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public void start() {

        while (true) {
            System.out.println("\n===== ADMIN PANEL =====");
            System.out.println("1. Manage Menu");
            System.out.println("2. Manage Discounts");
            System.out.println("3. Manage Delivery Partners");
            System.out.println("4. Exit");

            int choice = Validate.validateInt();

            switch (choice) {
                case 1 -> manageMenu();
                case 2 -> manageDiscounts();
                case 3 -> manageDeliveryPartners();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    // ================= MENU =================

    private void manageMenu() {
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. View Items");

        int choice = Validate.validateInt();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter price: ");
                double price = Validate.validateDouble();

                int id = adminService.addItem(name, price);
                System.out.println("Item added with id: " + id);
            }
            case 2 -> {
                System.out.print("Enter item id: ");
                int id = Validate.validateInt();
                boolean removed = adminService.removeItem(id);
                System.out.println(removed ? "Removed" : "Not found");
            }
            case 3 -> {
                List<FoodItem> items = adminService.getAllItems();
                items.forEach(i ->
                        System.out.println(i.getId() + " - " + i.getName()));
            }
        }
    }

    // ================= DISCOUNT =================

    private void manageDiscounts() {
        System.out.println("1. View Discounts");
        System.out.println("2. Add Amount Discount");

        int choice = Validate.validateInt();

        switch (choice) {
            case 1 -> adminService.getAllDiscounts()
                    .forEach(d ->
                            System.out.println(d.getId() + " - " + d.getDiscountPercentage()));

            case 2 -> {
                System.out.print("Enter amount: ");
                double amount = Validate.validateDouble();

                System.out.print("Enter percentage: ");
                double percentage = Validate.validateDouble();

                IDiscount discount = new AmountDiscount(amount, percentage);
                adminService.addDiscount(discount);
                System.out.println("Discount added");
            }
        }
    }

    // ================= DELIVERY PARTNER =================

    private void manageDeliveryPartners() {
        System.out.println("1. Add Partner");
        System.out.println("2. View Partners");

        int choice = Validate.validateInt();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                User partner = new DeliveryPartner(name, password, "", "");
                adminService.addDeliveryPartner(partner);

                System.out.println("Partner added with id: " + partner.getId());
            }
            case 2 -> adminService.getAllDeliveryPartners()
                    .forEach(p ->
                            System.out.println(p.getId() + " - " + p.getName()));
        }
    }
}