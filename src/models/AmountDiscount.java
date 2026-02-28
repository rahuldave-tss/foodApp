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

    public int getDiscountId() {
        return id;
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

    public double applyDiscount(){
        return Math.max(0,amount-(amount*discountPercentage)/100);
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
