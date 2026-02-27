package services;

import exceptions.EmptyMenuException;
import exceptions.ItemAlreadyPresentException;
import models.*;
import repos.DPRepo;
import repos.DiscountRepo;
import repos.UserRepo;

import java.util.List;

public class AdminService {

    private final MenuService menuService;
    private final DiscountRepo discountRepo;
    private final DPRepo dpRepo;
    private final UserRepo userRepo;

    public AdminService(MenuService menuService,
                        DPRepo dpRepo,
                        UserRepo userRepo,
                        DiscountRepo discountRepo) {
        this.menuService = menuService;
        this.dpRepo = dpRepo;
        this.userRepo = userRepo;
        this.discountRepo = discountRepo;
    }

    // ================= MENU =================

    public int addItem(String itemName, double price) {
        if (itemAlreadyPresent(itemName)) {
            throw new ItemAlreadyPresentException("Food Item already present in Menu");
        }
        return menuService.addFoodItem(itemName, price);
    }

    public boolean removeItem(int itemId) {
        if (menuService.getMenu().getItemList().isEmpty()) {
            throw new EmptyMenuException("No food items available");
        }
        return menuService.removeFoodItem(itemId);
    }

    public List<FoodItem> getAllItems() {
        return menuService.getMenu().getItemList();
    }

    private boolean itemAlreadyPresent(String itemName) {
        return menuService.getMenu().getItemList()
                .stream()
                .anyMatch(f -> f.getName().equalsIgnoreCase(itemName));
    }

    // ================= DISCOUNT =================

    public void addDiscount(IDiscount discount) {
        discountRepo.getAvailableDiscounts().add(discount);
    }

    public boolean removeDiscount(int discountId) {
        return discountRepo.getAvailableDiscounts()
                .removeIf(d -> d.getId() == discountId);
    }

    public boolean updateDiscount(int id, double newPercentage) {
        for (IDiscount discount : discountRepo.getAvailableDiscounts()) {
            if (discount.getId() == id) {
                discount.setDiscountPercentage(newPercentage);
                return true;
            }
        }
        return false;
    }

    public List<IDiscount> getAllDiscounts() {
        return discountRepo.getAvailableDiscounts();
    }

    // ================= DELIVERY PARTNER =================

    public User addDeliveryPartner(User partner) {
        userRepo.addUser(partner);
        dpRepo.addPartner(partner);
        return partner;
    }

    public boolean removeDeliveryPartner(int partnerId) {
        User partner = userRepo.getUserById(partnerId);
        if (partner == null) return false;

        userRepo.removeUser(partnerId);
        dpRepo.removePartner(partner);
        return true;
    }

    public List<User> getAllDeliveryPartners() {
        return dpRepo.getDeliveryPartners();
    }
}