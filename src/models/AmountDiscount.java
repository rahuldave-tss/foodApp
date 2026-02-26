package models;

import services.OrderService;
import utils.RandomNumberGenerator;

public class AmountDiscount implements IDiscount{
    private int id;
    private double amount;
    private int discountPercentage;

    public AmountDiscount(double amount, int discountPercentage) {
        id=RandomNumberGenerator.generateRandomNumber();
        this.amount = amount;
        this.discountPercentage = discountPercentage;
    }

    public int getId() {
        return id;
    }

    @Override
    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }


    public double getAmount() {
        return amount;
    }
}
