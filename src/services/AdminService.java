package services;

import exceptions.EmptyMenuException;
import exceptions.ItemAlreadyPresentException;
import models.*;
import repos.DPRepo;
import repos.DiscountRepo;
import repos.UserRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.GlobalConstants.scanner;
import static utils.Validate.validateDouble;
import static utils.Validate.validateInt;

public class AdminService {
    private MenuService menuService;
    private DiscountRepo discountRepo;
    private DPRepo dpRepo;
    private UserRepo userRepo;

    public AdminService(MenuService menuService,DPRepo dpRepo,UserRepo userRepo) {
        this.menuService = menuService;
        this.discountRepo = new DiscountRepo();
        this.dpRepo = dpRepo;
        this.userRepo = userRepo;
    }

    public void displayFeatures(){
        while(true) {
            System.out.println("===ADMIN PANEL===");
            System.out.println("Options available: ");
            System.out.println("1. Manage Discounts");
            System.out.println("2. Manage Items in Menu");
            System.out.println("3. Manage Delivery Partners");
            System.out.println("4. Back");
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
                    System.out.println("Back to Main Menu...");
                    return;
                }
                default:{
                    System.out.println("Enter a valid choice !!!");
                }
            }
        }
    }

    private void manageDeliveryPartners() {
        while(true){
            System.out.println();
            System.out.println("Options available: ");
            System.out.println("1. Add Partner");
            System.out.println("2. Remove Partner");
            System.out.println("3. Back");
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
                case 3:{
                    System.out.println("Back to Admin Panel...");
                    return;
                }
                default:{
                    System.out.println("Enter a valid choice !!");
                }
            }
        }
    }

    private void manageItems() {
        System.out.println();
        menuService.displayMenu();
        System.out.println();
        System.out.println("Options available: ");
        System.out.println("1. Create Food Item");
        System.out.println("2. Remove Food Item");
        System.out.print("Enter your choice: ");
        int choice=validateInt();
        switch (choice){
            case 1:{
                try{
                    addItem();
                }
                catch(ItemAlreadyPresentException e){
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 2:{
                try{
                    removeItem();
                }
                catch(EmptyMenuException e){
                    System.out.println(e.getMessage());
                }
                break;
            }
            default:{
                System.out.println("Enter a valid choice !!");
            }
        }
    }

    private void manageDiscounts() {
        while(true){
            System.out.println();
            System.out.println("Options available: ");
            System.out.println("1. Create Discount");
            System.out.println("2. Remove Discount");
            System.out.println("3. Update Existing Discount");
            System.out.println("4. Back");
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
                case 3:{
                    updateDiscountPercentage();
                    break;
                }
                case 4:{
                    System.out.println("Back to Admin Panel...");
                    return;
                }
                default:{
                    System.out.println("Enter a valid choice !!");
                }
            }
        }

    }

    private void updateDiscountPercentage() {
        System.out.println("Enter the Discount Id to update that discount percentage: ");
        int id=validateInt();
        if(discountRepo.getAvailableDiscounts().isEmpty()){
            System.out.println("No discounts available !!");
            return;
        }
        System.out.println("Enter new discount percentage: ");
        int newPercentage=validateInt();
        if(newPercentage<0 || newPercentage>100){
            System.out.println("Enter a valid discount percentage !!");
            return;
        }
        List<IDiscount> discounts=discountRepo.getAvailableDiscounts();
        for(IDiscount discount: discounts){
            if(discount.getId()==id){
                discount.setDiscountPercentage(newPercentage);
                System.out.println("Discount Percentage Updated !!");
                return;
            }
        }
        System.out.println("No such discount found !!");
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
        List<IDiscount> discounts=discountRepo.getAvailableDiscounts();
        discounts.add(newDiscount);
        discountRepo.setAvailableDiscounts(discounts);
    }

    public void removeDiscount(){
        if(discountRepo.getAvailableDiscounts().isEmpty()){
            System.out.println("No discounts available !!");
            return;
        }
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
        List<IDiscount> discounts=discountRepo.getAvailableDiscounts();
        discounts.remove(newDiscount);
        discountRepo.setAvailableDiscounts(discounts);
    }

    public void addItem(){
        System.out.print("Enter Item Name to add: ");
        String itemName=scanner.nextLine();
        if(itemAlreadyPresent(itemName)){
            throw new ItemAlreadyPresentException("Food Item already present in Menu !!");
        }
        System.out.print("Enter it's Price: ");
        double price=validateDouble();
        int foodItemId=menuService.addFoodItem(itemName,price);
        if(foodItemId!=-1){
            System.out.println("Food Item Added with ID: "+foodItemId);
            return;
        }
        System.out.println("Food Item already present in Menu !!");
    }

    private boolean itemAlreadyPresent(String itemName) {
        for(FoodItem f:menuService.getMenu().getItemList()){
            if(f.getName().equalsIgnoreCase(itemName))return true;
        }
        return false;
    }

    public void removeItem(){
        if(menuService.getMenu().getItemList().isEmpty()){
            throw new EmptyMenuException("No food items available in menu !!");
        }
        System.out.print("Enter Item ID to remove: ");
        int itemId= validateInt();
        if(menuService.removeFoodItem(itemId)){
            System.out.println("Food Item Removed !!");
            return;
        }
        System.out.println("Food Item not present in Menu !!");
    }

    public void addPartner(){
        System.out.println("Enter Name of partner: ");
        String partnerName= scanner.nextLine();
        System.out.println("Enter password for partner: ");
        String partnerPassword= scanner.nextLine();
        User partner=new User(partnerName,partnerPassword,UserType.DELIVERY_PARTNER);
        userRepo.addUser(partner);
        dpRepo.addPartner(partner);
        System.out.println("Delivery Partner Added with ID: "+partner.getId());
    }

    public void removePartner(){
        if(dpRepo.getDeliveryPartners().isEmpty()){
            System.out.println("No delivery partners available !!");
            return;
        }
        System.out.println("Enter the ID of the partner: ");
        int partnerId=validateInt();
        User partner= userRepo.getUserById(partnerId);
        if(partner==null){
            System.out.println("No such partner found");
            return;
        }
        userRepo.removeUser(partner.getId());
        dpRepo.removePartner(partner);
        System.out.println("Delivery Partner Removed of ID: "+partner.getId());
    }


    private IDiscount removeAmountDiscount() {
        System.out.print("Enter the Discount Id to remove that discount: ");
        int discountId=validateInt();
        if(discountRepo.getAvailableDiscounts().isEmpty()){
            System.out.println("No discounts available !!");
            return null;
        }
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
