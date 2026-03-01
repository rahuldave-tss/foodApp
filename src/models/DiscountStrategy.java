package models;

public interface DiscountStrategy {
    double applyDiscount(double amount);
    int getDiscountId();
    void setDiscountPercentage(double discountPercentage);
    double getDiscountPercentage();
    double getAmount();
}
