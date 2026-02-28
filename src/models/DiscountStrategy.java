package models;

public interface DiscountStrategy {
    double applyDiscount();
    int getDiscountId();
    void setDiscountPercentage(double discountPercentage);
    double getDiscountPercentage();
    double getAmount();
}
