package models;

import services.OrderService;

public class AmountDiscount implements IDiscount{
    private int id;
    private static int newId=1;
    private double amount;
    private int discountPercentage;

    public AmountDiscount(double amount, int discountPercentage) {
        id=newId;
        newId+=5;
        this.amount = amount;
        this.discountPercentage = discountPercentage;
    }

    public int getId() {
        return id;
    }
}
