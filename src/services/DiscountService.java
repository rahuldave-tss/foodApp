package services;

import models.DiscountStrategy;
import repos.DiscountRepo;

import java.util.List;

public class DiscountService {
    private DiscountRepo discountRepo;

    public DiscountService(DiscountRepo discountRepo) {
        this.discountRepo = discountRepo;
    }

    public double applyMaxDiscount(double cartTotal){
        double maxDiscount=0;
        List<DiscountStrategy> availableDiscounts=discountRepo.getAvailableDiscounts();
        for(DiscountStrategy discount:availableDiscounts){
            if(cartTotal>=discount.getAmount()){
                maxDiscount=Math.max(maxDiscount,discount.applyDiscount(cartTotal));
            }
        }
        return maxDiscount;
    }
}
