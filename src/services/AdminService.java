package services;

import exceptions.EmptyMenuException;
import exceptions.ItemAlreadyPresentException;
import models.*;
import repos.*;

import java.util.List;

public class AdminService {

    private final MenuRepo menuRepo;
    private final DiscountRepo discountRepo;
    private final DPRepo dpRepo;
    private final UserRepo userRepo;
    private CustomerRepo customerRepo;

    public AdminService(MenuRepo menuRepo,
                        DPRepo dpRepo,
                        UserRepo userRepo,
                        DiscountRepo discountRepo, CustomerRepo customerRepo) {
        this.menuRepo = menuRepo;
        this.dpRepo = dpRepo;
        this.userRepo = userRepo;
        this.discountRepo = discountRepo;
        this.customerRepo=customerRepo;
    }

    public int addItem(String itemName, double price) {
        if (itemAlreadyPresent(itemName)) {
            throw new ItemAlreadyPresentException("Food Item already present in Menu");
        }
        FoodItem foodItem=new FoodItem(itemName,price);
        menuRepo.addItem(foodItem);
        return foodItem.getId();
    }

    public boolean removeItem(int itemId) {
        if (menuRepo.getMenu().isEmpty()) {
            throw new EmptyMenuException("No food items available");
        }
        FoodItem foodItem=menuRepo.getFoodItemById(itemId);
        menuRepo.removeItem(foodItem);
        return true;
    }

    public List<FoodItem> getAllItems() {
        return menuRepo.getMenu();
    }

    private boolean itemAlreadyPresent(String itemName) {
        return menuRepo.getMenu()
                .stream()
                .anyMatch(f -> f.getName().equalsIgnoreCase(itemName));
    }

    public void addDiscount(DiscountStrategy discount) {
        discountRepo.getAvailableDiscounts().add(discount);
    }

    public boolean removeDiscount(int discountId) {
        return discountRepo.getAvailableDiscounts()
                .removeIf(d -> d.getDiscountId() == discountId);
    }

    public void updateDiscount(int id, double newPercentage) {
        DiscountStrategy discount= discountRepo.findById(id);
        if(discount==null){
            System.out.println("No such discount available !!");
            return;
        }
        discount.setDiscountPercentage(newPercentage);
    }

    public List<DiscountStrategy> getAllDiscounts() {
        return discountRepo.getAvailableDiscounts();
    }

    public void addDeliveryPartner(User partner) {
        userRepo.addUser(partner);
        dpRepo.addPartner((DeliveryPartner) partner);
    }

    public void removeDeliveryPartner(int partnerId) {
        DeliveryPartner partner = dpRepo.getDeliveryPartnerById(partnerId);
        if (partner == null){
            System.out.println("No such partner found !!");
            return;
        }

        userRepo.removeUser(partner.getId());
        dpRepo.removePartner(partner);
        System.out.println("Delivery Partner removed successfully !!");
    }

    public List<DeliveryPartner> getAllDeliveryPartners() {
        return dpRepo.getDeliveryPartners();
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.getCustomerList();
    }
}