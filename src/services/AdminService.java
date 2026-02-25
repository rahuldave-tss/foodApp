package services;

import models.*;
import repos.DPRepo;
import repos.DiscountRepo;
import repos.UserRepo;

import static utils.GlobalConstants.scanner;
import static utils.Validate.validateDouble;
import static utils.Validate.validateInt;

public class AdminService {
    private MenuService menuService;
    private DiscountRepo discountRepo;
    private DPRepo dpRepo;
    private UserRepo userRepo;

    public AdminService() {
        this.menuService = new MenuService();
        this.discountRepo = new DiscountRepo();
        this.dpRepo = new DPRepo();
        this.userRepo = new UserRepo();
    }

    public void displayFeatures(){
        System.out.println("===ADMIN PANEL===");
        System.out.println("Options available: ");
        System.out.println("1. Manage Discounts");
        System.out.println("2. Manage Items in Menu");
        System.out.println("3. Manage Delivery Partners");
        System.out.println("4. Exit");
        System.out.print("Enter you choice: ");
        int choice=validateInt();

        switch (choice){
            case 1:{
                manageDiscounts();
                break;
            }
            case 2:{
                manageItems();
                break;
            }
            case 3:{
                manageDeliveryPartners();
                break;
            }
            case 4:{
                System.out.println("Exiting...");
                return;
            }
            default:{
                System.out.println("Enter a valid choice !!!");
            }
        }
    }

    private void manageDeliveryPartners() {
        System.out.println();
        System.out.println("Options available: ");
        System.out.println("1. Add Partner");
        System.out.println("2. Remove Partner");
        System.out.print("Enter your choice: ");
        int choice=validateInt();
        switch (choice){
            case 1:{
                addPartner();
                break;
            }
            case 2:{
                removePartner();
                break;
            }
            default:{
                System.out.println("Enter a valid choice !!");
            }
        }
    }

    private void manageItems() {
        System.out.println();
        System.out.println("Options available: ");
        System.out.println("1. Create Food Item");
        System.out.println("2. Remove Food Item");
        System.out.print("Enter your choice: ");
        int choice=validateInt();
        switch (choice){
            case 1:{
                addItem();
                break;
            }
            case 2:{
                removeItem();
                break;
            }
            default:{
                System.out.println("Enter a valid choice !!");
            }
        }
    }

    private void manageDiscounts() {
        System.out.println();
        System.out.println("Options available: ");
        System.out.println("1. Create Discount");
        System.out.println("2. Remove Discount");
        System.out.print("Enter your choice: ");
        int choice=validateInt();
        switch (choice){
            case 1:{
                createDiscount();
                break;
            }
            case 2:{
                removeDiscount();
                break;
            }
            default:{
                System.out.println("Enter a valid choice !!");
            }
        }
    }

    public void createDiscount(){
        System.out.println("Which type of discount you want to add?: ");
        System.out.println("1. Amount Discount");
        int choice=validateInt();
        IDiscount newDiscount=null;
        switch (choice){
            case 1:{
                newDiscount=createAmountDiscount();
                break;
            }
            default:{
                System.out.println("Enter a valid option !!");
            }
        }
        discountRepo.addDiscount(newDiscount);
    }

    public void removeDiscount(){
        System.out.println("Which type of discount you want to remove?: ");
        System.out.println("1. Amount Discount");
        int choice=validateInt();
        IDiscount newDiscount=null;
        switch (choice){
            case 1:{
                newDiscount=removeAmountDiscount();
                break;
            }
            default:{
                System.out.println("Enter a valid option !!");
            }
        }
        if(newDiscount==null){
            System.out.println("No Discount Found !!");
            return;
        }
        discountRepo.removeDiscount(newDiscount);
    }

    public void addItem(){
        System.out.print("Enter Item Name to add: ");
        String itemName=scanner.nextLine();
        System.out.print("Enter it's Price: ");
        double price=validateDouble();
        if(menuService.addFoodItem(itemName,price)){
            System.out.println("Food Item Added !!!");
            return;
        }
        System.out.println("Food Item already present in Menu !!");
    }

    public void removeItem(){
        System.out.print("Enter Item Name to remove: ");
        String itemName= scanner.nextLine();
        if(menuService.removeFoodItem(itemName.toLowerCase())){
            System.out.println("Food Item Removed !!");
            return;
        }
        System.out.println("Food Item not present in Menu !!");
    }

    public void addPartner(){
        System.out.println("Enter the ID of the partner: ");
        int partnerId=validateInt();
        System.out.println("Enter Name of partner: ");
        String partnerName= scanner.nextLine();
        System.out.println("Enter password for partner: ");
        String partnerPassword= scanner.nextLine();
        User partner=new User(partnerName,partnerPassword,UserType.DELIVERY_PARTNER);
        userRepo.getUserList().put(partner.getId(),partner);
        System.out.println("Delivery Partner Added with ID: "+partner.getId());
    }

    public void removePartner(){
        System.out.println("Enter the ID of the partner: ");
        int partnerId=validateInt();
        User partner= userRepo.getUserById(partnerId);
        if(partner==null){
            System.out.println("No such partner found");
            return;
        }
        userRepo.getUserList().remove(partner.getId());
        System.out.println("Delivery Partner Removed of ID: "+partner.getId());
    }


    private IDiscount removeAmountDiscount() {
        System.out.print("Enter the Discount Id to remove that discount: ");
        int discountId=validateInt();
        for(IDiscount discount: discountRepo.getAvailableDiscounts()){
            if(discount.getId()==discountId){
                return discount;
            }
        }
        return null;
    }

    private IDiscount createAmountDiscount() {
        System.out.print("Enter Total Amount: ");
        double amount=validateDouble();
        System.out.print("Enter Discount Percentage: ");
        int discountPercentage=validateInt();
        return new AmountDiscount(amount,discountPercentage);
    }

}
