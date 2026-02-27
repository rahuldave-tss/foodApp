package models;

import services.OrderService;
import utils.RandomNumberGenerator;

public class AmountDiscount implements IDiscount{
    private int id;
    private double amount;
    private double discountPercentage;

    public AmountDiscount(double amount, double discountPercentage) {
        id=RandomNumberGenerator.generateRandomNumber();
        this.amount = amount;
        this.discountPercentage = discountPercentage;
    }

    public int getId() {
        return id;
    }

    @Override
    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }


    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "AmountDiscount{" +
                "id=" + id +
                ", amount=" + amount +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
