package controllers;

import models.IDiscount;
import services.AdminService;

import static utils.Validate.validateInt;

public class AdminController {
//    private AdminService adminService;
//
//    public AdminController(AdminService adminService) {
//        this.adminService = adminService;
//    }
//
//    public void displayFeatures(){
//        while(true) {
//            System.out.println("===ADMIN PANEL===");
//            System.out.println("Options available: ");
//            System.out.println("1. Manage Discounts");
//            System.out.println("2. Manage Items in Menu");
//            System.out.println("3. Manage Delivery Partners");
//            System.out.println("4. Back");
//            System.out.print("Enter you choice: ");
//            int choice=validateInt();
//
//            switch (choice){
//                case 1:{
//                    manageDiscounts();
//                    break;
//                }
//                case 2:{
//                    manageItems();
//                    break;
//                }
//                case 3:{
//                    manageDeliveryPartners();
//                    break;
//                }
//                case 4:{
//                    System.out.println("Back to Main Menu...");
//                    return;
//                }
//                default:{
//                    System.out.println("Enter a valid choice !!!");
//                }
//            }
//        }
//    }
//
//    private void manageDiscounts() {
//        while (true) {
//            System.out.println();
//            System.out.println("Options available: ");
//            System.out.println("1. Create Discount");
//            System.out.println("2. Remove Discount");
//            System.out.println("3. Back");
//            System.out.print("Enter your choice: ");
//            int choice = validateInt();
//            switch (choice) {
//                case 1: {
//                    createDiscount();
//                    break;
//                }
//                case 2: {
//                    removeDiscount();
//                    break;
//                }
//                case 3: {
//                    System.out.println("Back to Admin Panel...");
//                    return;
//                }
//                default: {
//                    System.out.println("Enter a valid choice !!");
//                }
//            }
//        }
//    }
//
//    private void createDiscount() {
//        System.out.println("Which type of discount you want to create?: ");
//        System.out.println("1. Amount Discount");
//        int choice=validateInt();
//        IDiscount newDiscount=null;
//        switch (choice){
//            case 1:{
//                newDiscount=createAmountDiscount();
//                break;
//            }
//            default:{
//                System.out.println("Enter a valid option !!");
//            }
//        }
//        adminService.createDiscount(newDiscount);
//    }
//
//    private void manageItems() {
//        System.out.println();
//        menuService.displayMenu();
//        System.out.println();
//        System.out.println("Options available: ");
//        System.out.println("1. Create Food Item");
//        System.out.println("2. Remove Food Item");
//        System.out.print("Enter your choice: ");
//        int choice=validateInt();
//        switch (choice){
//            case 1:{
//                addItem();
//                break;
//            }
//            case 2:{
//                removeItem();
//                break;
//            }
//            default:{
//                System.out.println("Enter a valid choice !!");
//            }
//        }
//    }
//
//    private void manageDeliveryPartners() {
//        while(true){
//            System.out.println();
//            System.out.println("Options available: ");
//            System.out.println("1. Add Partner");
//            System.out.println("2. Remove Partner");
//            System.out.println("3. Back");
//            System.out.print("Enter your choice: ");
//            int choice=validateInt();
//            switch (choice){
//                case 1:{
//                    addPartner();
//                    break;
//                }
//                case 2:{
//                    removePartner();
//                    break;
//                }
//                case 3:{
//                    System.out.println("Back to Admin Panel...");
//                    return;
//                }
//                default:{
//                    System.out.println("Enter a valid choice !!");
//                }
//            }
//        }
//    }

}
