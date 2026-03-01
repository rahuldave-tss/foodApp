package models;

import utils.RandomNumberGenerator;

public class AmountDiscount implements DiscountStrategy {
    private int id;
    private String discountName;
    private double amount;
    private double discountPercentage;

    public AmountDiscount(String discountName,double amount, double discountPercentage) {
        id=RandomNumberGenerator.generateRandomNumber();
        this.discountName=discountName;
        this.amount = amount;
        this.discountPercentage = discountPercentage;
    }

    public int getId() {
        return id;
    }

    @Override
    public double applyDiscount(double amount) {
        return Math.max(0, amount*this.discountPercentage/100);
    }

    public int getDiscountId() {
        return id;
    }

    public String getDiscountName() {
        return discountName;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
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
